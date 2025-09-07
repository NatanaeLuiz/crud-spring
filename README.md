# 📘 Guia para Criar um Projeto Spring Boot - turma 4º Periodo do curso de Ciências da Computação - Faculdade Católica

Este guia mostra os passos para criar um projeto **Spring Boot** do zero, utilizando **Maven** como gerenciador de dependências.

---

## 🔧 Pré-requisitos

Antes de começar, certifique-se de ter instalado:
- [Java JDK 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- Uma IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Postman ou navegador para testar os endpoints

---

## 📦 Criando o Projeto

### 1. Usando Spring Initializr (mais fácil)
Acesse 👉 [https://start.spring.io](https://start.spring.io)  
<img width="1760" height="830" alt="image" src="https://github.com/user-attachments/assets/2d5ad2d8-6dd2-40af-acee-a6b7c8ba7d79" />

Configure:
- **Project**: Maven
- **Language**: Java
- **Spring Boot**: versão estável mais recente
- **Packaging**: Jar
- **Java**: 17+

Dependências sugeridas:
- **Spring Web**
- H2
- JDBC

Baixe o projeto e extraia em sua máquina.

---

### 2. Criando via Maven (manual)
Se quiser criar pelo terminal:

**Windows (PowerShell)**

```powershell
mvn archetype:generate `
"-DgroupId=com.exemplo" `
"-DartifactId=meu-projeto-spring" `
"-DarchetypeArtifactId=maven-archetype-quickstart" `
"-DinteractiveMode=false"
```

**Linux (Bash)**
```bash 
mvn archetype:generate -DgroupId=com.exemplo \
    -DartifactId=meu-projeto-spring \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false
```

Startar Projeto com maven:
```bash
mvn spring-boot:run
```

