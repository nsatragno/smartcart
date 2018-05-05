Rails.application.routes.draw do
  resources :productos
  devise_for :usuarios
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html

  root "productos#index"
end
