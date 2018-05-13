Rails.application.routes.draw do
  resources :changos do
    member do
      get 'qr'
    end
  end
  resources :tags
  resources :productos

  devise_for :usuarios
  resources :usuarios

  root "productos#index"
end
