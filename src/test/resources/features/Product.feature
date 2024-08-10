Feature: Validación del Precio de un Producto

  Scenario: Validar el precio del producto en el carrito
    Given estoy en la página de la tienda
    And me logueo con mi usuario "lvallash@gmail.com" y clave "Michul2703*"
    When navego a la categoría "Clothes" y subcategoría "Men"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el título de la página del carrito
    And vuelvo a validar el cálculo de precios en el carrito
