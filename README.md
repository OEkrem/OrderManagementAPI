# ORDER MANAGMENT API
  - Bu API, sipariş süreçlerini kolaylaştırmak için geliştirilmiş olup, sipariş oluşturma, güncelleme, kullanıcı yönetimi gibi özellikler sunar.

## PROJE YAPISI
```
src/
├── aspects/             # Uygulama genelinde AOP (Aspect-Oriented Programming) işlemlerini yönetir
├── config/              # Uygulama yapılandırmalarını içerir (AppConfig, ModelMapperConfig vb.)
├── controller/          # API endpoint'lerini yöneten controller sınıfları
├── dto/                 # Response, Request ve CustomMapper sınıflarını içerir
├── email/               # E-posta ile ilgili işlemleri yöneten sınıflar
├── exceptions/          # Özel istisnaları yönetir
├── helppostman/         # Postman üzerinden CRUD işlemleri için yardımcı dokümantasyon
├── models/              # Veritabanı model sınıfları (User, Order, vb.)
├── repository/          # Hibernate işlemleri ve interface sınıfları ile veritabanı bağlantısı sağlar
├── scheduler/           # Zamanlanmış görevleri (Scheduled Jobs) yöneten sınıflar
├── security/            # Güvenlik yapılandırmaları ve yetkilendirme işlemleri (JWT, Authentication, vb.)
└── services/            # İş mantığını yöneten servis sınıfları
```

## Kullanılan Teknolojiler 🚀
 - Java 21
 - Spring Boot
 - Spring Data JPA – MySQL (Geliştirme), H2 (Test)
 - Spring Security – JWT Refresh-Access token ile kimlik doğrulama
 - Spring Boot Mail – E-posta işlemleri için
 - RabbitMQ – Olay yönetimi (Event Handling)
 - Mockito – Birim testler için
 - Lombok – Kod sadeleştirme (Getter, Setter, Constructor vb.)
 - Validation – Giriş doğrulama işlemleri için
 - ModelMapper 3.2.2 – Nesne dönüşümleri (Tercihe bağlı)
 - Maven – Bağımlılık yönetimi ve proje yapısı

## API Dokümentasyonu
Models: **`User`**, **`Address`**, **`Category`**, **`Product`**, **`Order`**, **`OrderDetail`**, **`Payment`**

## USER PROCESS
- ### User READ
  - GET - /api/users
  - GET - /api/users/{id}
- ### User ADD
  - POST - /api/users
    {
        "username": "deneme37",
        "password": "sifre",
        "firstName": "firstName",
        "lastName": "lasname",
        "email": "firstname@example.com",
        "phone": "5323212121"
    }
- ### User UPDATE
  - PUT - /api/users/{id}
    {
        "username": "deneme37",
        "password": "sifre",
        "firstName": "firstName",
        "lastName": "lasname",
        "email": "firstname@example.com",
        "phone": "5323212121"
    }
- ### User DELETE
  - DELETE - /api/users/{id}


## ADDRESSES PROCESS
- ### Address READ
  - GET - /api/addresses
  - GET - /api/addresses/{id}
  - GET - /api/addresses/users/{id}
- ### Address ADD
  - POST - /api/addresses/users/{id}
    {
        "name": "Address Name",
        "doorNumber": 5,
        "floor": 0,
        "buildingNumber": "58",
        "street": "Street Name",
        "city": "City Name",
        "country": "Country Name"
    }
- ### Address UPDATE
  - PUT - /api/addresses/users/{userId}
    {
        "id": 4,
        "name": "Address Name",
        "doorNumber": 5,
        "floor": 0,
        "buildingNumber": "No1",
        "street": "Street",
        "city": "City",
        "country": "Country"
    }
- ### Address DELETE
  - DELETE - /api/addresses/{id}


## CATEGORY PROCESS
- ### Category READ
  - GET - /api/categories
  - GET - /api/categories/{id}
- ### Category ADD
  - POST - /api/categories
    {
        "name": "Category Name",
        "description": "Category Description"
    }
- ### Category UPDATE
  - PUT - /api/categories/{id}
    {
        "name": "Category Name",
        "description": "Category Description"
    }
- ### Category DELETE
  - DELETE - /api/categories/{id}


## PRODUCT PROCESS
- ### Product READ
  - GET - /api/products
  - GET - /api/products/{id}

- ### Product ADD
  - GET - /api/products
    {
        "name": "Phone Name",
        "categoryId": 11,
        "description": "Telephone Description",
        "price": 7000.00,
        "image": "image/url/phone.jpeg"
    }
- ### Product UPDATE
  - PUT - api/products/id
    {
        "name": "Phone Name",
        "categoryId": 11,
        "description": "Phone Description",
        "price": 7000.00,
        "image": "image/url/phone.jpeg"
    }
- ### Product DELETE
  - Delete - api/products/id


## ORDER PROCESS
- ### READ  ORDER
  - GET - /api/orders
  - GET - /api/orders/{orderId}
  - GET - /api/orders/all
- ### ADD  ORDER
  - POST - /api/orders/users/{userId}
    {
        "date": "2024-02-07",
        "total": 150
    }
- ### UPDATE  ORDER
  - PUT - /api/orders/users/{userId}
    {
        "id": 1,
        "date": "2024-02-07",
        "total": 150
    }
- ### DELETE  ORDER
  - DELETE - /api/orders/{orderId}


## ORDERDETAIL PROCESS
- ### READ  ORDERDETAIL
  - GET - /api/orderdetails
  - GET - /api/orderdetails/{id}
  - GET - /api/orderdetails/orders/{orderId}

- ### ADD  ORDERDETAIL
  - POST - /api/orderdetails/orders/{orderId}
    {
        "productId": 1,
        "quantityType": "PIECE",
        "quantity": 1,
        "price": 150
    }

- ### UPDATE  ORDERDETAIL
  - PUT - /api/orderdetails/orders/{orderId}
    {
        "id": 1,
        "productId": 1,
        "quantityType": "PIECE",
        "quantity": 1,
        "price": 150
    }

- ### DELETE  ORDERDETAIL
  - DELETE - /api/orderdetails/{id}


## PAYMENT PROCESS
- ### READ  PAYMENT
  - GET - /api/payments
  - GET - /api/payments/{id}
- ### ADD  PAYMENT
  -  POST - /api/payments/orders/{orderId}
  {
      "description": "Payment Description",
      "amount": 150,
      "paymentStatus": "PENDING",
      "paymentMethod": "CREDIT_CARD",
      "date": "2024-02-07T14:30:00"
  }
- ### UPDATE  PAYMENT
  - PUT - /api/payments/orders/{orderId}
  {
      "id": 1,
      "description": "Payment Description",
      "amount": 150,
      "paymentStatus": "PENDING",
      "paymentMethod": "CREDIT_CARD",
      "date": "2024-02-07T14:30:00"
  }
- ### DELETE  PAYMENT
  - DELETE - /api/payments/{id}



## İLETİŞİM
E-posta: oekremyildirim@outlook.com  
LinkedIn: [Profil Linki](https://www.linkedin.com/in/onur-ekrem-y%C4%B1ld%C4%B1r%C4%B1m-8b2010263/)  
GitHub: [Profil Linki](https://github.com/OEkrem)

