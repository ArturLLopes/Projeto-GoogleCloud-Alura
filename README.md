# ☁️ Projeto Google Cloud - Alura

[![Google Cloud](https://img.shields.io/badge/Google%20Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)](https://cloud.google.com/)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

Este projeto demonstra como desenvolver e publicar uma aplicação Java com Spring Boot na **Google Cloud Platform (GCP)**, utilizando as melhores práticas de DevOps e infraestrutura como código.

> 🎓 Desenvolvido como parte do curso **"Google Cloud: Infraestrutura como serviço"** da [Alura](https://www.alura.com.br)

## 📸 Demonstração

![Demonstração do app em execução](https://github.com/user-attachments/assets/a23eb513-2a41-48d5-ae70-511be40697f7)
[CRUD no ar](https://api-cors-1-798462238118.southamerica-east1.run.app/swagger-ui/index.html#/)
## 🚀 Funcionalidades

- **API REST completa** com Spring Boot
- **Banco de dados SQLite** para persistência
- **Deploy automático** via Docker na Google Cloud
- **CI/CD integrado** com Google Cloud Build
- **Monitoramento** e logs centralizados

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem principal da API |
| **Spring Boot** | 3.x | Framework web robusto |
| **SQLite** | 3.x | Banco de dados leve e eficiente |
| **Docker** | 24.x | Containerização da aplicação |
| **Google Cloud Run** | - | Execução serverless da API |
| **Google Cloud Build** | - | Build e deploy automatizado |
| **Google Secret Manager** | - | Gerenciamento seguro de credenciais |


## ⚙️ Executando Localmente

### 📋 Pré-requisitos

- [Java 21+](https://www.oracle.com/java/technologies/downloads/)

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Google Cloud SDK](https://cloud.google.com/sdk/docs/install)

### 🔧 Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/ArturLLopes/Projeto-GoogleCloud-Alura.git
   cd Projeto-GoogleCloud-Alura
   ```

2. **Compile e execute a aplicação**
   ```bash
   # Compilar o projeto
   mvn clean compile
   
   # Executar a aplicação
   mvn spring-boot:run
   ```

✅ **Aplicação disponível em:** [http://localhost:8080](http://localhost:8080)

## 🐳 Executando com Docker

### Build e execução local

```bash
# Build da imagem
docker build -t projeto-alura .

# Execute o container
docker run -d -p 8080:8080 --name projeto-alura projeto-alura

# Visualize os logs
docker logs projeto-alura
```

### Docker Compose (recomendado para desenvolvimento)

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=development
    volumes:
      - ./database:/app/database
```

```bash
docker-compose up -d
```

## ☁️ Deploy na Google Cloud

### 🔐 Configuração inicial

1. **Autenticação**
   ```bash
   gcloud auth login
   gcloud auth application-default login
   ```

2. **Configuração do projeto**
   ```bash
   gcloud config set project [SEU_PROJETO_ID]
   gcloud config set run/region southamerica-east1
   ```

3. **Habilitar APIs necessárias**
   ```bash
   gcloud services enable run.googleapis.com
   gcloud services enable cloudbuild.googleapis.com
   gcloud services enable secretmanager.googleapis.com
   ```

### 🚀 Deploy via Cloud Build

```bash
# Build e deploy automatizado
gcloud builds submit --config cloudbuild.yaml

# Ou deploy direto
gcloud run deploy --port=8080 \
  
```

### 📊 Configuração de monitoramento

```bash
# Habilitar logs estruturados
gcloud logging sinks create projeto-alura-sink \
  bigquery.googleapis.com/projects/[SEU_PROJETO_ID]/datasets/logs
```

## 🧪 Testes e Qualidade

### Executar testes

```bash
# Instalar dependências de teste (já incluído no pom.xml)
mvn clean test

# Executar testes com relatório
mvn test jacoco:report

# Relatório de cobertura disponível em: target/site/jacoco/index.html
```

### Linting e formatação

```bash
# Verificar formatação e qualidade do código
mvn checkstyle:check

# Análise estática com SpotBugs
mvn spotbugs:check

# Verificar dependências desatualizadas
mvn versions:display-dependency-updates
```

## 📈 Monitoramento e Observabilidade

### Métricas importantes

- **Latência**: Tempo de resposta das requisições
- **Throughput**: Número de requisições por segundo
- **Errors**: Taxa de erro 4xx/5xx
- **Saturação**: Utilização de CPU/memória

### Alertas recomendados

```bash
# Alerta para alta latência
gcloud alpha monitoring policies create --policy-from-file=monitoring/latency-alert.yaml

# Alerta para alta taxa de erro
gcloud alpha monitoring policies create --policy-from-file=monitoring/error-rate-alert.yaml
```

## 🔄 CI/CD Pipeline

O arquivo `cloudbuild.yaml` configura um pipeline completo:

- ✅ **Build**: Construção da imagem Docker
- ✅ **Test**: Execução de testes automatizados
- ✅ **Security**: Scan de vulnerabilidades
- ✅ **Deploy**: Deploy automático no Cloud Run
- ✅ **Notification**: Notificações no Slack/Email



## 🏆 Créditos

**Desenvolvido por:** [Artur Lopes](https://github.com/ArturLLopes) 👨‍💻

**Curso:** [Google Cloud: Alura](https://www.alura.com.br)

## 🔗 Links Úteis

- 📚 [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- ☁️ [Google Cloud Run](https://cloud.google.com/run)
- 🎓 [Alura Cursos](https://www.alura.com.br)
- 🐳 [Docker Hub](https://hub.docker.com/)
- 🔐 [Secret Manager](https://cloud.google.com/secret-manager)
- ☕ [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)

---

⭐ **Se este projeto te ajudou, considere dar uma estrela no GitHub!**
