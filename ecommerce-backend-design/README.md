# 🛍 ShopWave — eCommerce Backend

**Developershub Intern Task | Rafay | Spring Boot + MySQL + Thymeleaf**

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Spring Boot 3.2.5 |
| Security | Spring Security + JWT (JJWT 0.12.6) |
| Database | MySQL 8+ |
| Frontend | Thymeleaf + HTML/CSS/JS |
| Build | Maven |

---

## Project Structure

```
src/main/java/com/ecommerce/
├── EcommerceApplication.java       ← Entry point
├── controller/
│   ├── HomeController.java         ← GET /
│   ├── ProductController.java      ← GET /products, /products/{id}, /search
│   ├── AuthController.java         ← GET/POST /auth/login, /auth/signup
│   └── AdminController.java        ← /admin/** (ADMIN role only)
├── model/
│   ├── Product.java
│   └── User.java
├── repository/
│   ├── ProductRepository.java
│   └── UserRepository.java
├── service/
│   ├── ProductService.java
│   ├── AuthService.java
│   └── DataSeeder.java             ← Seeds sample data on first run
├── security/
│   ├── JwtUtil.java
│   ├── JwtAuthFilter.java
│   ├── UserDetailsServiceImpl.java
│   └── SecurityConfig.java
└── dto/
    ├── AuthDto.java
    └── ProductDto.java

src/main/resources/
├── templates/
│   ├── index.html                  ← Home page
│   ├── fragments/navbar.html       ← Shared navbar
│   ├── products/
│   │   ├── list.html               ← Product listing + pagination
│   │   └── detail.html             ← Product detail
│   ├── auth/
│   │   ├── login.html
│   │   └── signup.html
│   └── admin/
│       ├── dashboard.html
│       └── product-form.html       ← Add / Edit product
├── static/
│   ├── css/style.css
│   └── js/app.js
└── application.properties
```

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8+
- VS Code with "Extension Pack for Java" (or IntelliJ)

### 2. Configure Database

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

> The database `ecommerce_db` will be created automatically.

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or in VS Code: open `EcommerceApplication.java` → click **Run**.

### 4. Open in Browser

```
http://localhost:8080
```

---

## 🔑 Default Login Credentials

| Role  | Email            | Password  |
|-------|-----------------|-----------|
| Admin | admin@shop.com  | admin123  |
| User  | user@shop.com   | user123   |

> These are seeded automatically on first run.

---

## 📋 Routes

| Route | Access | Description |
|-------|--------|-------------|
| `GET /` | Public | Home page with featured products |
| `GET /products` | Public | All products with pagination |
| `GET /products/{id}` | Public | Product detail |
| `GET /search?query=...` | Public | Server-side search |
| `GET /auth/login` | Public | Login page |
| `GET /auth/signup` | Public | Signup page |
| `GET /auth/logout` | Any | Clears JWT cookie |
| `GET /admin` | ADMIN | Dashboard |
| `GET /admin/products/new` | ADMIN | Add product form |
| `POST /admin/products/save` | ADMIN | Save new product |
| `GET /admin/products/edit/{id}` | ADMIN | Edit product form |
| `POST /admin/products/update/{id}` | ADMIN | Update product |
| `POST /admin/products/delete/{id}` | ADMIN | Delete product |

---

## Week Deliverables Checklist

### ✅ Week 1 — Project Setup & Static Routing
- [x] Spring Boot server running
- [x] Routes: `/`, `/products`, `/products/{id}`
- [x] Responsive layout (mobile + desktop)
- [x] Static CSS with media queries

### ✅ Week 2 — Database & Dynamic Content
- [x] MySQL database integration
- [x] Products entity with full schema
- [x] Dynamic Thymeleaf rendering on all pages
- [x] Sample data seeded (10 products, 5 categories)
- [x] Server-side search by name or category
- [x] Category filter on product listing

### ✅ Week 3 — Auth, Features & Deployment
- [x] JWT authentication (stored in HttpOnly cookie)
- [x] Signup and Login pages
- [x] Role-based access (USER / ADMIN)
- [x] Add product form (Admin only)
- [x] Edit & Delete product (Admin only)
- [x] Pagination on product listing

---

## Deployment (Week 3)

### Option A — Render (Recommended, Free)
1. Push repo to GitHub
2. Go to [render.com](https://render.com) → New Web Service
3. Connect your GitHub repo
4. Set environment:
   - Build: `mvn clean package -DskipTests`
   - Start: `java -jar target/ecommerce-backend-design-0.0.1-SNAPSHOT.jar`
5. Add environment variables for DB (use Render's free MySQL or Aiven free tier)

### Option B — Railway
1. Push to GitHub
2. New project on [railway.app](https://railway.app)
3. Add MySQL plugin → copy connection string to env vars

---

## GitHub Repository Name
`ecommerce-backend-design`
