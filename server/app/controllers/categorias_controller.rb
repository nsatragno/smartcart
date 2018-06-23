class CategoriasController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_gestion

  before_action :set_categoria, only: [:show, :edit, :update, :destroy]

  def index
    @categorias = Categoria.all
  end

  def new
    @categoria = Categoria.new
  end

  def create
    @categoria = Categoria.new(categoria_params)
    if @categoria.save
      flash[:success] = "Categoría creada con éxito"
      return redirect_to action: "index"
    end
    render :new
  end

  def update
    if @categoria.update(categoria_params)
      flash[:success] = "Categoría actualizada con éxito"
      return redirect_to action: "index"
    end
    render :edit
  end

  def destroy
    @categoria.destroy
    flash[:success] = "Categoría eliminada con éxito"
    return redirect_to action: "index"
  end

  private

  def categoria_params
    params.require(:categoria).permit(:nombre, :posicion_x, :posicion_y)
  end

  def set_categoria
    @categoria = Categoria.find(params[:id])
  end
end
