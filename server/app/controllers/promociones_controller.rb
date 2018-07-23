class PromocionesController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_gestion
  before_action :set_promocion, only: [:show, :edit, :update, :destroy]

  def index
    @promociones = Promocion.all
  end

  def new
    @promocion = Promocion.new
  end

  def edit
  end

  def create
    @promocion = Promocion.new promocion_params
    if @promocion.save
      flash[:success] = "Promoción creada con éxito"
      return redirect_to promociones_path
    end
    render :new
  end

  def update
    if @promocion.update promocion_params
      flash[:success] = "Promoción actualizada con éxito"
      return redirect_to promociones_path
    end
    render :edit
  end

  def destroy
    @promocion.destroy!
    flash[:success] = "Promoción eliminada con éxito"
    redirect_to promociones_path
  end

  private

  def set_promocion
    @promocion = Promocion.find(params[:id])
  end

  def promocion_params
    params.require(:promocion).permit(:nombre, :imagen)
  end
end
