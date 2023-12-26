import com.currency.convertor.client.FixerIoApiClient;
import com.currency.convertor.client.RabbitMQSender;
import com.currency.convertor.domain.dto.CurrencyApiResponse;
import com.currency.convertor.domain.entity.CurrencyData;
import com.currency.convertor.repository.CurrencyDataRepository;
import com.currency.convertor.service.rates.impl.CurrencyRatesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CurrencyRatesServiceTest {
    private FixerIoApiClient apiClient;
    private RabbitMQSender mqClient;
    private CurrencyDataRepository currencyDataRepository;
    private CurrencyRatesServiceImpl currencyRatesService;

    public CurrencyRatesServiceTest() {
    }

    @Before()
    public void init() {
        this.apiClient = Mockito.mock(FixerIoApiClient.class);
        this.mqClient = Mockito.mock(RabbitMQSender.class);
        this.currencyDataRepository = Mockito.mock(CurrencyDataRepository.class);
        this.currencyRatesService = Mockito.mock(CurrencyRatesServiceImpl.class);
    }


    /*@Test
    public void fetchDataAndStoreInDatabase_Failure() throws ParseException {
        CurrencyApiResponse mockApiResponse = createMockApiResponse(false);
        when(apiClient.getCurrencyApiResponse()).thenReturn(mockApiResponse);
        String apiUrl = currencyRatesService.fetchDataAndStoreInDatabase();
        verify(currencyDataRepository, never()).save(any());
        verify(mqClient, never()).sendMessageToCurrencyStats(anyString());

    }*/
    @Test
    public void fetchDataAndStoreInDatabase_Success() throws ParseException {
        CurrencyApiResponse mockApiResponse = createMockApiResponse(true);
        when(apiClient.getCurrencyApiResponse()).thenReturn(mockApiResponse);
        when(currencyDataRepository.save(any())).thenReturn(new CurrencyData());

        this.currencyRatesService = new CurrencyRatesServiceImpl(this.apiClient, this.mqClient, this.currencyDataRepository);
        String apiUrl = currencyRatesService.fetchDataAndStoreInDatabase();
        verify(apiClient, times(1)).getCurrencyApiResponse();
        verify(currencyDataRepository, times(mockApiResponse.getRates().size())).save(any());
        verify(mqClient, times(mockApiResponse.getRates().size() * 2)).sendMessageToCurrencyStats(anyString());
        assertEquals(apiClient.getApiUrl(), apiUrl);
    }

    @Test
    public void fetchDataAndStoreInDatabase_Failure() throws ParseException {
        CurrencyApiResponse mockApiResponse = new CurrencyApiResponse();
        mockApiResponse.setSuccess(false);
        when(apiClient.getCurrencyApiResponse()).thenReturn(mockApiResponse);
        this.currencyRatesService = new CurrencyRatesServiceImpl(this.apiClient, this.mqClient, this.currencyDataRepository);
        String apiUrl = currencyRatesService.fetchDataAndStoreInDatabase();

        verify(apiClient, times(1)).getCurrencyApiResponse();
        verify(currencyDataRepository, never()).save(any());
        verify(mqClient, never()).sendMessageToCurrencyStats(anyString());


        assertEquals(apiClient.getApiUrl(), apiUrl);
    }

    private CurrencyApiResponse createMockApiResponse(boolean success) throws ParseException {
        String dateString = "2023-01-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        CurrencyApiResponse mockApiResponse = new CurrencyApiResponse();
        mockApiResponse.setSuccess(success);
        mockApiResponse.setDate(date);
        mockApiResponse.setTimestamp(1234567890L);

        Map<String, Double> rates = Map.of("USD", 1.0, "EUR", 0.9);
        mockApiResponse.setRates(rates);

        return mockApiResponse;
    }
}
