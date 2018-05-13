Rails.application.routes.draw do
  resources :changos do
    member do
      get 'qr'
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
