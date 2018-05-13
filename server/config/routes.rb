Rails.application.routes.draw do
  resources :changos
  resources :tags
  resources :productos

  devise_for :usuarios
  resources :usuarios

  root "productos#index"
end
