Feature: Login

  @test
  Scenario: Iniciar sesión en la tienda
    Given que me encuentro en la página de login de la tienda en línea
    When inicio sesión con las credenciales usuario: "lvallash@gmail.com" y contraseña: "Michul2703*"
    Then valido que debería aparecer el título de "Home"
    And también valido que al menos exista un item
