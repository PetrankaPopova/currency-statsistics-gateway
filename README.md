# currency-statistics-gateway
Currency Gateway Application
Objective
The Currency Gateway Application serves as a facade to provide currency data to different customer types. The application collects current currency data from fixer.io, stores it in a relational database, and offers public REST APIs for external services. The system also handles duplicate requests, maintains statistical information, and forwards data through a message broker.

##Components:
1. Data Collector
   Collects and stores current currency data from fixer.io.
   Periodically refreshes data based on predefined time intervals.
2. REST APIs for External Services
   Supports two public REST APIs, EXT_SERVICE_1 and EXT_SERVICE_2.
   Handles requests with content types application/json and application/xml.
   EXT_SERVICE_1 (JSON API)
   /json_api/current: Accepts POST requests for current currency data.
   /json_api/history: Accepts POST requests for historical currency data.
   EXT_SERVICE_2 (XML API)
   /xml_api/command: Accepts POST requests in XML format for both current and historical data.
3. Statistical Information Collector
   Collects unified statistical information in a relational database.
   Includes service name/id (EXT_SERVICE_X), request id, timestamp (UTC), and end client id.
4. Message Broker Integration
   Forwards unified information about incoming requests and exchange rates through a message broker.
   Usage
   Ensure proper configuration for data refresh intervals.
   Access EXT_SERVICE_1 and EXT_SERVICE_2 APIs with the specified formats.
   Statistical information is automatically collected and stored in the database.
   Setup Instructions
   Clone the repository.
   Configure the application properties for database connectivity and fixer.io API access.
   Build and run the application using your preferred Java environment.
   Contribution Guidelines
   Fork the repository.
   Create a new branch for your feature or bug fix.
   Make changes and commit with descriptive messages.
   Create a pull request, referencing the issue (if applicable).
   Issues and Bug Reports
   Feel free to open issues or submit pull requests for any bugs you may encounter.

##License
This project is licensed under the MIT License.

##Acknowledgments
fixer.io for providing currency data.
Spring Boot for the framework.
RabbitMQ for message broker integration.