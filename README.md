# Rappi_Test
Examen aplicado en Rappi

La aplicacion funcionando la puedes encontrar en :
https://www.youtube.com/watch?v=6S3hJSFsWnw&feature=share

En la aplicacion se utilizaron los siguiente componentes:
-Retrofit para consumir api
-Room para manejar los datos en Cache
-Fragmentos
-Actividades
-LiveData
-Callbacks
-ViewModel

La estructura del proyecto esta constituida de la siguiente manera:

 Carpeta Adapters: En ella se encuentran los adaptadores para los recyclerview y viewPager.
 Carpeta DataBase: En ella estan las clases e Interfaces para llebar acabo la parte de Cache.
 Carpeta IO : En ella se encuentran las clases que nos permitiran llebar acabo la conexion con el servidor(Retrofit).
 Carpeta Model : En ella se encuentran el modelo para las peliculas
 Carpeta Repository: En ella se encuentra nuestra clase que interactura entre los datos y los ViewModels.
 Carpeta ViewModel  :En ella se encuentran todos nuestros ViewModel para interactuar con las vistas.
 
 Finalmente tenemos 4 vistas MainActivity que contiene un viewPager que contiene 3 fragmentos uno para cada
 categoria el fragmento Detalle para ver la imagen selecciona en el reciclerview y finalmente la 
 actividad de search donde se podran hacer busquedas en Room.
 
Basicamente se Intento arquitecturar de manera MVVM donde las actividades y fragmentos(CategoryFragment,DetailsFragment,MainActivity y SearchActivity)interactuan con los ViewModel(MainViewModel,MoviesViewModel,SearchViewModel) para lograr mostrar los datos en UI
Donde los ViewModel  toma los datos atravez de Repository(AppRepository) donde esta toma los datos del Model(AppDataBase y RetrofitClient) 


1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
Establece que cada módulo o clase debe tener responsabilidad sobre una sola parte de la funcionalidad proporcionada por el software
y esta responsabilidad debe estar encapsulada en su totalidad por la clase.
 
 Mantener las propiedades seguras.
 Mantenimiento de codigo.
 Facil testeo 
 Reduccion de lineas de codigo.
 


2. Qué características tiene, según su opinión, un “buen” código o código limpio? 

	-Documentado  (Lo cual no logre hacer por falta de tiempo :( )
	-Patrones de Diseno bien implementados.
	-Bien arquiteturado.
	-Modulado.
	-Que las clases y funciones cumplan con la responsabilidad unica.
