Rails.application.routes.draw do
  resources :changos do
    member do
      get 'qr'
      get 'insertar_tag/:rfid', action: 'insertar_tag', as: 'insertar_tag'
      get 'remover_tag/:rfid', action: 'remover_tag', as: 'remover_tag'
    end
  end
  resources :tags
  resources :productos do
    member do
      get 'imagen'
    end
  end

  devise_for :usuarios
  resources :usuarios

  root "productos#index"
end
