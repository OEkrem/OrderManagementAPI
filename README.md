# ORDER MANAGMENT API v1.2
 - Java ve Spring Framework kullanılarak MVC tasarım deseni ile geliştirilmiş, RESTful API aracılığıyla iletişim kuran, monolitik bir e-ticaret platformunun backend uygulamasıdır.
 - Pagination ve filtering yapıları ile lazy loading uygulanarak performans optimizasyonu sağlanmıştır.
 - HTTP Cookies üzerinden saklanan Refresh Token ve Access Token mekanizması ile güvenlik artırılmıştır.

## PROJE YAPISI
```
src/
├── aspects/             # Uygulama genelinde AOP (Aspect-Oriented Programming) işlemlerini yönetir
├── config/              # Uygulama yapılandırmalarını içerir (SecurityConfig, OpenApiConfig vb.)
├── controller/          # API endpoint'lerini yöneten controller sınıfları
├── dto/                 # Response, Request ve Mapper sınıflarını içerir
├── email/               # E-posta ile ilgili işlemleri yöneten sınıflar
├── exceptions/          # Özel istisnaları yönetir
├── models/              # Veritabanı model sınıfları (User, Order, vb.)
├── repository/          # Hibernate işlemleri ve interface sınıfları ile veritabanı bağlantısı sağlar
├── scheduler/           # Zamanlanmış görevleri (Scheduled Jobs) yöneten sınıflar
├── security/            # Güvenlik yapılandırmaları ve yetkilendirme işlemleri (JWT, Authentication, vb.)
├── services/            # İş mantığını yöneten servis sınıfları
└── validation/          # Custom validation sınıflarını içerir
```

## Kullanılan Teknolojiler 🚀
 - Java 21
 - Spring Boot
 - Spring Data JPA – MySQL (Geliştirme), H2 (Test)
 - Spring AOP - Aspects
 - Spring Security – Authentication ve Authorization with JWT Refresh-Access Token
 - Spring Boot Mail – E-posta işlemleri için
 - Mockito – Birim testler için
 - MapStruct - Dto / entity dönüşümleri için
 - Validation – Giriş doğrulama işlemleri için
 - Lombok – Kod sadeleştirme (Getter, Setter, Constructor vb.)
 - Swagger - Api dokümentasyonu için
 - RabbitMQ – Olay yönetimi (Event Handling)
 - Maven – Bağımlılık yönetimi ve proje yapısı

## Uygulanan Yapılar ve Teknikler
- Pagination & Filtering Design
- DTO Kullanımı
- Builder Design Pattern
- Docker Compose ile Kolay Kurulum – Uygulama ve bağımlılıklarını tek komutla başlatmak için


## API Dokümentasyonu
Models: **`User`**, **`Address`**, **`Category`**, **`Product`**, **`Order`**, **`OrderDetail`**, **`Payment`**
 - API dokümantasyonu için Swagger UI'yi kullanabilirsiniz:
🔗 http://localhost:8090/swagger-ui.html

### Kurulum ve Çalıştırma
 - ``mvn clean install``
 - ``docker-compose up`` 
 - ``mvn spring-boot:run``

---

# Example Workflow
### 1️⃣ Kullanıcı Kaydı - Register
**Endpoint:** `POST /api/v1/auth/register`  
**Request Body:**
```json
{
    "username": "john_doe",
    "password": "password123",
    "email": "john@example.com"
}
```
Response:
``
    "message": "Kullanıcı başarıyla oluşturuldu."
``

### 2️⃣ Kullanıcı Girişi - Login
**Endpoint:** `POST /api/v1/auth/login`  
**Request Body:**
```json
{
      "email": "jhon@example.com",
      "password": "password123"
}
```
**Response:** `accessToken` and `expiresIn` 

### 3️⃣ Sipariş Oluşturma - Create Order
**Endpoint:** `POST /api/v1/orders`  
**Request Body:**
```json
{
      "userId": "16"
}
```
**Response:** `id` `userId` `date` `orderStatus`

### 4️⃣ Siparişe Ürün Ekleme - Add OrderDetail
**EndPoint:** ``PATCH /api/v1/orders/{orderId}/orderdetail``  
**Path Variable:** `orderId`
```json
{
      "productId": "1",
      "quantityType": "BOX",
      "quantity": "1"
}
```
**Response:** `id` `productId` `quantityType` `quantity` `price`

### 5️⃣ Ödeme Tamamlama - Add Payment
**EndPoint:** ``PATCH /api/v1/orders/{orderId}/payment``  
**Path Variable:** `orderId`
```json
{
      "orderId": "1",
      "paymentStatus": "PENDING",
      "paymentMethod": "BANK_TRANSFER"
}
```
**Response:** `id` `amount` `paymentStatus` `paymentMethod` `date` `orderId`

### 6️⃣ Siparişi Onayla - Confirm Order
**EndPoint:** ``PATCH /api/v1/orders/{orderId}/confirm``  
**Path Variable:** `orderId`  
**Response:** `id` `userId` `date` `orderStatus`

---

## İLETİŞİM
E-posta: oekremyildirim@outlook.com  
LinkedIn: [Profil Linki](https://www.linkedin.com/in/onur-ekrem-y%C4%B1ld%C4%B1r%C4%B1m-8b2010263/)  
GitHub: [Profil Linki](https://github.com/OEkrem)

