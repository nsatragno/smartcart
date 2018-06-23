class ProductosController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_gestion
  before_action :set_producto, only: [:show, :edit, :update, :destroy, :imagen]

  # GET /productos
  # GET /productos.json
  def index
    @productos = Producto.all
  end

  # GET /productos/1.json
  def show
  end

  # GET /productos/new
  def new
    @producto = Producto.new
  end

  # GET /productos/1/edit
  def edit
  end

  # POST /productos
  # POST /productos.json
  def create
    @producto = Producto.new(producto_params)

    respond_to do |format|
      if @producto.save
        flash[:success] = 'Producto creado con éxito'
        format.html { redirect_to action: "index" }
      else
        format.html { render :new }
        format.json { render json: @producto.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /productos/1
  # PATCH/PUT /productos/1.json
  def update
    respond_to do |format|
      if @producto.update(producto_params)
        flash[:success] = 'Producto actualizado con éxito'
        format.html { redirect_to action: "index" }
        format.json { render :show, status: :created, location: @producto }
      else
        format.html { render :edit }
        format.json { render json: @producto.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /productos/1
  # DELETE /productos/1.json
  def destroy
    @producto.destroy
    flash[:success] = 'Producto eliminado con éxito'
    respond_to do |format|
      format.html { redirect_to productos_url }
      format.json { head :no_content }
    end
  end

  def imagen
    send_file @producto.imagen.path, type: @producto.imagen.content_type, disposition: "inline"
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_producto
      @producto = Producto.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def producto_params
      params.require(:producto).permit(:nombre, :precio, :imagen, :apto_celiacos,
                                       :apto_diabeticos, :descripcion, :categoria_id)
    end
end
