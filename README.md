BoticaFX - Sistema de Gestión Farmacéutica

Este proyecto es una aplicación de escritorio diseñada para optimizar la gestión operativa de una botica. El sistema permite el control de inventarios, administración de usuarios y procesos de venta, integrando una interfaz gráfica moderna y fluida.

🛠️ Tecnologías y Herramientas

Lenguaje de Programación: Java.

Framework de Interfaz: JavaFX (uso de archivos FXML para diseño de vistas).

Arquitectura de Software: Patrón M¿V?C (Modelo-¿Vista?-Controlador) para asegurar la escalabilidad y orden del código.

Gestor de Dependencias: Maven (configuración vía pom.xml).

Persistencia de Datos: Manejo de archivos de datos (.dat) para el almacenamiento de información del inventario.

🚀 Funcionalidades Principales

Módulo de Autenticación: Sistema de Login seguro con manejo de sesiones de usuario (LoginController, Sesion.java).

Gestión de Inventario: Control detallado de productos y medicamentos, permitiendo el registro y consulta de stock (Inventario.java, Medicamento.java).

Administración de Usuarios: Gestión de perfiles y permisos para el personal de la botica (GestionUsers.java, Usuario.java).

Interfaz Principal: Dashboard interactivo para la navegación entre los diferentes módulos del sistema (ventana-principal.fxml).

📂 Estructura del Proyecto

controlador/: Lógica de control que gestiona la interacción del usuario con la interfaz.

modelo/: Definición de entidades de negocio y lógica de procesamiento de datos.

resources/: Vistas FXML y recursos gráficos del sistema.

🔧 Instalación y Uso

Clonar el repositorio.

Asegurarse de tener instalado el JDK de Java y Maven.

Ejecutar la clase principal Launcher.java.


//Este proyecto utiliza persistencia mediante archivos binarios (.dat).
