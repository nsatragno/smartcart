class SupermercadosController < ApplicationController
  before_action :authenticate_usuario!, except: [:show]
  before_action :validar_gestion, except: [:show]

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
    respond_to do |format|
      format.html {
        authenticate_usuario!
        validar_gestion
        render :edit
      }
      format.json {
        render :show
      }
    end
  end

  private

  def supermercado_params
    params.require(:supermercado).permit([:plano, :nombre])
  end

  def set_supermercado
    @supermercado = Supermercado.first || Supermercado.new
  end
end
