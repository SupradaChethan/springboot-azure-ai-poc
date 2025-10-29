# Spring Boot Azure OpenAI Chat Application

A Spring Boot-based chatbot application powered by Azure OpenAI, featuring persistent chat history and an interactive web interface.

## Overview

This proof-of-concept demonstrates the integration of Spring Boot with Azure OpenAI services to create an intelligent chatbot. The application provides a REST API and web interface for users to interact with Azure's GPT models, with all conversations stored in an H2 database.

## Features

- **Azure OpenAI Integration**: Leverages Azure OpenAI's chat completion API with GPT-4.1-mini model
- **Interactive Chat UI**: Modern floating chat bubble interface for seamless conversations
- **Persistent Chat History**: All conversations are stored in an H2 database with timestamps
- **RESTful API**: Clean REST endpoints for chat interactions and history retrieval
- **Spring AI Framework**: Built on Spring AI 1.0.0-M5 for streamlined AI service integration
- **H2 Database Console**: Built-in database console for inspecting chat history
- **CORS Enabled**: Cross-origin support for flexible frontend integration

## Prerequisites

Before running this application, ensure you have:

- **Java 17** or higher
- **Maven 3.6+** (or use the included Maven wrapper)
- **Azure Account** with access to Azure OpenAI Service
- **Azure OpenAI Resource** deployed with a chat completion model

## Azure OpenAI Setup

1. Create an Azure OpenAI resource in the [Azure Portal](https://portal.azure.com)
2. Deploy a chat model (e.g., `gpt-4.1-mini`)
3. Navigate to your resource and obtain:
   - **API Key**: Found in "Keys and Endpoint" section
   - **Endpoint URL**: Found in "Keys and Endpoint" section
   - **Deployment Name**: The name you gave to your deployed model

## Configuration

Update the `src/main/resources/application.properties` file with your Azure OpenAI credentials:

```properties
# Azure OpenAI Configuration
spring.ai.azure.openai.api-key=<YOUR_AZURE_OPENAI_API_KEY>
spring.ai.azure.openai.endpoint=<YOUR_AZURE_OPENAI_ENDPOINT>
spring.ai.azure.openai.chat.options.deployment-name=gpt-4.1-mini
spring.ai.azure.openai.api-version=2024-12-01-preview
```

## Running the Application

### Using Maven

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Using Maven Wrapper (Windows)

```bash
# Build the project
mvnw.cmd clean install

# Run the application
mvnw.cmd spring-boot:run
```

### Using Maven Wrapper (Linux/Mac)

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Chat API

#### Submit a Chat Message

```http
POST /api/chat/ask
Content-Type: application/json

{
  "prompt": "What is Spring Boot?"
}
```

**Response:**
```json
{
  "id": 1,
  "userInput": "What is Spring Boot?",
  "aiResponse": "Spring Boot is a framework...",
  "chatTime": "2025-10-29T10:30:00Z"
}
```

#### Get Chat History

```http
GET /api/chat/history
```

**Response:**
```json
[
  {
    "id": 1,
    "userInput": "What is Spring Boot?",
    "aiResponse": "Spring Boot is a framework...",
    "chatTime": "2025-10-29T10:30:00Z"
  }
]
```

### Web Interface

- **Chat UI**: `http://localhost:8080/`
- **H2 Console**: `http://localhost:8080/h2-console`

## H2 Database Access

Access the H2 database console at `http://localhost:8080/h2-console`

**Connection Settings:**
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

Query chat history:
```sql
SELECT * FROM chat_history ORDER BY chat_time DESC;
```

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.5.5
- **Spring AI**: 1.0.0-M5
- **Spring Data JPA**: For database operations
- **H2 Database**: In-memory database for chat history
- **Lombok**: For reducing boilerplate code
- **Azure OpenAI**: GPT-4.1-mini model
- **Maven**: Build and dependency management

## Project Structure

```
springboot-azure-ai-poc/
├── src/main/java/com/sup/sbai/
│   ├── SbaiApplication.java              # Main application entry point
│   ├── controller/
│   │   ├── AiController.java            # REST API endpoints
│   │   └── UIController.java            # UI routing
│   ├── service/
│   │   └── AiService.java               # Business logic for AI interactions
│   ├── domain/
│   │   └── ChatHistory.java             # JPA entity for chat storage
│   ├── dao/
│   │   └── ChatHistoryRepository.java   # Data access layer
│   └── dto/
│       ├── ChatRequest.java             # Request DTO
│       └── ChatResponse.java            # Response DTO
├── src/main/resources/
│   ├── application.properties           # Application configuration
│   ├── static/
│   │   └── chat.html                    # Floating chat bubble UI
│   └── templates/
│       └── chat.html                    # Alternative chat template
├── pom.xml                              # Maven configuration
└── mvnw, mvnw.cmd                       # Maven wrapper scripts
```

## Key Components

### ChatHistory Entity (domain/ChatHistory.java)

Stores each conversation with fields:
- `id`: Unique identifier
- `userInput`: User's question/prompt
- `aiResponse`: AI-generated response
- `chatTime`: Timestamp of the interaction

### AiService (service/AiService.java)

Core service handling:
- Communication with Azure OpenAI via `ChatClient`
- Persisting chat history to the database
- Retrieving historical conversations

### AiController (controller/AiController.java)

REST controller providing:
- `POST /api/chat/ask`: Submit chat prompts
- `GET /api/chat/history`: Retrieve all conversations

## Development

### Building the Project

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

### Accessing Actuator Endpoints

Spring Boot Actuator endpoints are available for monitoring:
- Health: `http://localhost:8080/actuator/health`
- Info: `http://localhost:8080/actuator/info`

## Troubleshooting

### Common Issues

**Issue**: `401 Unauthorized` when calling Azure OpenAI
- **Solution**: Verify your API key and endpoint in `application.properties`

**Issue**: `Model deployment not found`
- **Solution**: Ensure the deployment name in configuration matches your Azure OpenAI deployment

**Issue**: Database connection errors
- **Solution**: H2 uses in-memory storage; data is lost on restart. This is expected behavior.

## Future Enhancements

- [ ] User authentication and session management
- [ ] PostgreSQL or MySQL for production persistence
- [ ] Conversation threading and context management
- [ ] Rate limiting and request throttling
- [ ] Export chat history functionality
- [ ] Multi-user support with separate chat histories
- [ ] WebSocket support for real-time streaming responses

## License

This project is a proof-of-concept for demonstration purposes.

## Contributing

Feel free to fork this repository and submit pull requests for improvements.

## Contact

For questions or support, please open an issue in the repository.

---

Built with Spring Boot and Azure OpenAI
