Rails.application.routes.draw do
  resources :promociones
  resources :categorias
  resources :changos do
    member do
      get 'qr'
      get 'insertar_tag/:rfid', action: 'insertar_tag', as: 'insertar_tag'
      get 'remover_tag/:rfid', action: 'remover_tag', as: 'remover_tag'
    end
  end
  resources :tags do
    collection do
      get 'por_rfid'
    end
  end
  resources :productos do
    member do
      get 'imagen'
    end
  end

  resources :cajas

  resource :supermercado

  devise_for :usuarios
  resources :usuarios

  root "dashboard#index"
end
