#language: es
@testfeature
Característica: Product - Store

  @test
    Escenario: Validar el precio del producto en el carrito
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "lvallash@gmail.com" y clave "Michul2703*"
    Cuando navego a la categoría "Clothes" y subcategoría "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el título de la página del carrito
    Y vuelvo a validar el cálculo de precios en el carrito