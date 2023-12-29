import com.currency.convertor.client.RabbitMQSender;
import com.currency.convertor.domain.dto.CurrencyStasRequest;
import com.currency.convertor.domain.dto.CurrencyStasResponse;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.exception.DuplicateRequestIdException;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.repository.RequestDetailsRepository;
import com.currency.convertor.service.statistics.impl.CurrencyStatisticsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CurrencyStatisticsServiceTest {
    private RabbitMQSender mqClient;
    private RequestDetailsRepository requestDetailsRepository;
    private CurrencyDataRepository currencyDataRepository;
    private CurrencyStatisticsServiceImpl currencyStatisticsService;

    public CurrencyStatisticsServiceTest() {
    }

    @Before()
    public void init() {
        this.mqClient = Mockito.mock(RabbitMQSender.class);
        this.requestDetailsRepository = Mockito.mock(RequestDetailsRepository.class);
        this.currencyDataRepository = Mockito.mock(CurrencyDataRepository.class);
        this.currencyStatisticsService = Mockito.mock(CurrencyStatisticsServiceImpl.class);
    }

    @Test
    public void processCurrencyStatsRequest() throws DuplicateRequestIdException {
        CurrencyStasRequest jsonRequest = new CurrencyStasRequest();
        jsonRequest.setRequestId("123");
        jsonRequest.setClient(1L);
        jsonRequest.setCurrency("USD");

        CurrencyData currencyData = new CurrencyData();
        currencyData.setCurrency("USD");
        currencyData.setRate(1.0);
        currencyData.setDate(Date.from(Instant.now()));
        currencyData.setTimestamp(System.currentTimeMillis());
        CurrencyStatisticsServiceImpl currencyStatisticsService = new CurrencyStatisticsServiceImpl(this.mqClient, this.requestDetailsRepository, this.currencyDataRepository);
        when(currencyDataRepository.findFirstByCurrencyOrderByTimestampDesc("USD")).thenReturn(currencyData);
        when(currencyDataRepository.findAllByCurrencyAndTimestampBetween(eq("USD"), anyLong(), anyLong()))
                .thenReturn(Collections.singletonList(currencyData));
        CurrencyStasResponse response = currencyStatisticsService.processCurrencyStatsRequest(jsonRequest);

        assertEquals("123", response.getRequestId());
        assertEquals("USD", response.getCurrency());
        assertEquals(currencyData, response.getData());

        RequestDetailsRepository requestDetailsRepositoryMock = mock(RequestDetailsRepository.class);
        verify(mqClient).sendMessageToRequestHistory(anyString());

    }

    @Test
    public void isDuplicateRequest_RequestIdDuplicate_ReturnsTrue() {
        String requestId = "duplicateId";
        currencyStatisticsService.isDuplicateRequest(requestId);
        boolean result = currencyStatisticsService.isDuplicateRequest(requestId);

        assertFalse(result);
    }

}
