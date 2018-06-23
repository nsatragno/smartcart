class SupermercadosController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_gestion

  before_action :set_supermercado

  def update
    if @supermercado.update(supermercado_params)
      flash[:success] = 'Información actualizada con éxito'
    else
      flash[:error] = 'Hubo errores al intentar actualizar la información'
    end
    render :edit
  end

  def create
    if @supermercado.update(supermercado_params)
      flash[:success] = 'Información dada de alta con éxito'
    end
    render :edit
  end

  def show
    render :edit
  end

  private

  def supermercado_params
    params.require(:supermercado).permit([:plano, :nombre])
  end

  def set_supermercado
    @supermercado = Supermercado.first || Supermercado.new
  end
end
