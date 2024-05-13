# Pshop

Semestrální projekt k předmětu NNPIA, Univerzita Pardubice. Semestrální projekt je praktickou částí předmětu NNPIA,
který se zabývá vývojem webových aplikací pomocí Spring Boot a React.

## Obsah

- [Overview](#overview)
- [Backend](#backend-srcmainjava)
- [Frontend](#frontend-srcmainweb)
- [Tests](#tests-srctest)
- [Other Files](#other-files)
- [Dependencies](#dependencies)

## Overview

Projekt je fiktivním eshop, který nabízí produkty z okruhu fotografování. Webová aplikace nabízí registraci a přihlášení
uživatelů. Uživatelé mají možnost procházet seznam nabízených produktů. Produkty lze filtrovat a řadit. Přihlášení
uživatelé pak mají možnost manipulovat s nabízeným zbožím v košíku. Zboží v košíku pak lze "zabalit" do jedné
objednávky.

## Backend `src.main.java`

### Package `component`

- **DatabaseRunner**: Zajistí úvodní naplnění databáze. Při nasazení by neměla být tato třída potřeba.

### Package `configuration`

- **SpringConfiguration**: Třída nastavuje CORS a zpřístupňuje modifikovaný AuthenticationProvider,
  AuthenticationManager a PasswordEncoder

### Package `controller`

- **UserController**: Kontroler pro uživatele.
- **OrderController**: Kontroler pro objednávky.
- **CartController**: Kontroler pro nákupní košíky.
- **ItemController**: Kontroler pro produkty. Rozšířen o **CameraController** a **LensController** pro specifické druhy
  produktů.

### Package `dto`

- **MinimalOrderDTO**: Objekt používaný při minimalistickém výpisu objednávek uživatele. *B -> F*
- **MinimalOrderItemDTO**: Podpůrný objekt pro **MinimalOrderDTO**. *B -> F*
- **OrderDTO**: Objekt pro objednávku. Používaný při vytváření objednávky. *F -> B*
- **OrderItemDTO**: Objekt pro objednávku. Používaný při vytváření objednávky. *F -> B*
- **OrderAddressDTO**: Objekt pro adresu. Používaný při vytváření objednávky. *F -> B*
- **UserNameDTO**: Objekt pro úpravu údajů uživatele. *F -> B*
- **UserPasswordDTO**: Objekt pro úpravu hesla uživatele. *F -> B*

### Package `entity`

- **User**: Uživatel.
- **Role**: Role.
- **Order**: Objednávka.
- **Cart**: Nákupní košík.
- **Item**: Produkt. Rozšířen o **Camera** a **Lens** pro specifikaci fotoaparátu a objektivu.
- **CartItem**: Produkt košíku. Vytváří spojení mezi produktem a nákupním košíkem.
- **OrderItem**: Produkt objednávky. Vytváří spojení mezi produktem a objednávkou.
- **Enums**: Výčtové typy.

### Package `exception`

- **CustomExceptionHandler**: Třída pro odchyt specifických vyjímek.
- **ExceptionResponse**: Objekt pro předávání vyjímek. *B -> F*

### Package `repository`

- **UserRepository**: Datová vrstva pro uživatele.
- **RoleRepository**: Datová vrstva pro role.
- **OrderRepository**: Datová vrstva pro objednávky.
- **CartRepository**: Datová vrstva pro nákupní košíky.
- **ItemRepository**: Datová vrstva pro produkty. Rozšířeno o **CameraRepository** a **LensRepository** pro fotoaparáty
  a objektivy.
- **CartItemRepository**: Datová vrstva pro produkty nákupních košíků.
- **OrderItemRepository**: Datová vrstva pro produkty objednávek.

### Package `service`

- **ServiceI**: Rozhraní servisní vrstvy.
- **UserService**: Servisní vrstva pro uživatele.
- **OrderService**: Servisní vrstva pro objednávky.
- **CartService**: Servisní vrstva pro nákupní košíky.
- **ItemService**: Servisní vrstva pro produkty. Rozšířeno o **CameraService** a **LensService** pro fotoaparáty a
  objektivy.
- **CartItemService**: Servisní vrstva pro produkty nákupních košíků.
- **OrderItemService**: Servisní vrstva pro produkty objednávek.

### Package `security`

- **AuthController**: Kontroler pro autentizaci. Zařizuje registraci a přihlašování uživatelů.
- **AuthService**: Servisní vrstva pro autentizaci. Zařizuje registraci a přihlašování uživatelů.
- **SecurityConfiguration**: Zpřístupňuje modifikovaný SecurityFilterChain.
- **JwtService**: Servisní vrstva pro JWT. Zpřístupňuje metody pro tvorbu JWT. Stejně tak obsahuje metody pro ověřování
  uživatele pomocí JWT.
- **JwtFilter**: Specializovaný filter pro ověření uživatele pomocí autentizační hlavičky.
- **RegisterRequest**: Objekt používaný při registraci uživatele. *F -> B*
- **LoginRequest**: Objekt používaný při přihlašování uživatele. *F -> B*
- **AuthenticationResponse**: Objekt, který slouží jako odpověď po úspěšné autentizaci. Obsahuje vygenerovaný JWT. *B ->
  F*

## Frontend `src.main.web`

### Package `assets`

- Obsahuje prvky jako jsou obrázky nebo písma.

### Package `public`

- Obsahuje prvky jako ikony nebo náhledy produktů.

### Package `style`

- Obsahuje stylizační .css soubory.

### Package `routes`

- **app**: Hlavní skript pro přesměrovávání uživatele na správnou koncovou stránku.
- **home**: Domovská stránka.
- **items**: Stránka s produkty.
- **detail**: Detail produktu.
- **shopping-cart**: Detail nákupního košíku.
- **user**: Uživatelské údaje a objednávky.
- **sign-up**: Registrace.
- **sign-in**: Přihlášení.
- **components**: Komponenty stránek.

## Tests `src.test`

### Package `controller`

- **CartControllerTest**: Test získání nákupního košíku přihlášeného uživatele.
- **UserControllerTest**: Test získání existujícího a neexistujícího uživatele.

### Package `service`

- **OrderServiceTest**: Test netriviální funkcionality. Vypočítání celkové ceny objednávky.
- **UserCartServiceTest**: Test spolupracujících servisní vrstvy uživatele a nákupního košíku. Každý nově zaregistrovaný
  uživatel by měl mít vytvořený nákupní košík.

## Other Files

- `src.main.resources` application.yaml
- docker-compose.yaml
- build.gradle

## Dependencies

Všechny závislosti jsou v souboru **build.gradle**. Pro spuštění je nutné mít staženy všechny závislosti a nainstalovaný
Docker, Node.js, Vite a React.

### Startup

- */* `docker-compose up`
- */src/main/web/* `npm run dev`
- */src/main/java/* `java App`