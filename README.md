# ORDER MANAGMENT API
  - Bu API, sipariş süreçlerini kolaylaştırmak için geliştirilmiş olup, sipariş oluşturma, güncelleme, ödeme işlemleri, kullanıcı yönetimi gibi özellikler sunar.

## PROJE YAPISI
```
src/
├── config/              # Uygulama yapılandırmalarını içerir (AppConfig, ModelMapperConfig vb.)
├── controller/          # API endpoint'lerini yöneten controller sınıfları
├── dataAccess/          # Hibernate işlemleri ve interface sınıfları ile veritabanı bağlantısı sağlar
├── dto/                 # Response, Request ve CustomMapper sınıflarını içerir
├── exceptions/          # Özel istisnaları yönetir
├── models/              # Veritabanı model sınıfları (User, Order, vb.)
├── security/            # Security için gerekli sınıflar (UserDetails, UserDetailService, vb.)
└── services/            # İş mantığını yöneten servis sınıfları
```

## Kullanılan Teknolojiler
  - Java 21.0.1
  - Spring Boot
  - MySQL Connector-j 8.3.0
  - Mockito-core 5.0.0
  - Lombok
  - Maven
  - Spring Security - jjwt
  - Validation
  - ModelMapper 3.2.2 - Tercihe bağlı

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
        "email": "firstname@hotmail.com",
        "phone": "5323212121"
    }
- ### User UPDATE
  - PUT - /api/users/{id}
    {
        "username": "deneme37",
        "password": "sifre",
        "firstName": "firstName",
        "lastName": "lasname",
        "email": "firstname@hotmail.com",
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
        "name": "Address_Name2",
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
        "name": "Home",
        "doorNumber": 5,
        "floor": 0,
        "buildingNumber": "4/6",
        "street": "Faruk Nafiz Çamlıbel",
        "city": "Istanbul",
        "country": "Turkiye"
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
        "name": "Telefonlar",
        "description": "Iphone Marka Telefon"
    }
- ### Category UPDATE
  - PUT - /api/categories/{id}
    {
        "name": "Telefonlar",
        "description": "Iphone Marka Telefon"
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
        "name": "Iphone 7s",
        "categoryId": 11,
        "description": "Iphone Marka Telefon",
        "price": 7000.00,
        "image": "image/url/iphone7s.jpeg"
    }
- ### Product UPDATE
  - PUT - api/products/id
    {
        "name": "Iphone 7s",
        "categoryId": 11,
        "description": "Iphone Marka Telefon",
        "price": 7000.00,
        "image": "image/url/iphone7s.jpeg"
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
      "description": "Description information",
      "amount": 150,
      "paymentStatus": "PENDING",
      "paymentMethod": "CREDIT_CARD",
      "date": "2024-02-07T14:30:00"
  }
- ### UPDATE  PAYMENT
  - PUT - /api/payments/orders/{orderId}
  {
      "id": 1,
      "description": "Description information",
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

