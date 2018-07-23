class CajasController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_cajera

  def index
    @changos = Chango.all.find_all do |chango|
      chango.en_uso?
    end
  end

  def edit
    @chango = Chango.find(params[:id])
  end
end
