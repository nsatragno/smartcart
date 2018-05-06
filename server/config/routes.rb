Rails.application.routes.draw do
  resources :tags
  resources :productos

  devise_for :usuarios
  resources :usuarios

  root "productos#index"
end
