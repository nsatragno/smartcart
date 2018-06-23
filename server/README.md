# SmartCart Server

## Configuración de Desarrollo

* Instalar Ruby
  * [En Windows: a partir del subsistema de linux para windows](https://gorails.com/setup/windows/10)
  * En Linux:

        sudo apt-get install build-essential ruby bundler

* Instalar dependencias:

      cd ruta_a_smartcart/server`
      bundle install


* Crear base de datos local

      rails db:create
      rails db:migrate
      rails db:seed

## Correr Server en Desarrollo

    rails s

Y visitar http://localhost:3000 cuando haya cargado.

Ver credenciales por defecto en archivo `db/seeds.rb`
