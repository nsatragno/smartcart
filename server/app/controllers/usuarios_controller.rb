class UsuariosController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_admin
  before_action :set_usuario, only: [:show, :edit, :update, :destroy]

  # GET /usuarios
  def index
    @usuarios = Usuario.all
  end

  # GET /usuarios/new
  def new
    @usuario = Usuario.new
  end

  # GET /usuarios/1/edit
  def edit
  end

  # POST /usuarios
  def create
    @usuario = Usuario.new(usuario_params)

    respond_to do |format|
      if @usuario.save
        flash[:success] = 'Usuario creado con éxito'
        format.html { redirect_to action: "index" }
      else
        format.html { render :new }
      end
    end
  end

  # PATCH/PUT /usuarios/1
  def update
    respond_to do |format|
      if @usuario.update(usuario_params)
        flash[:success] = 'Usuario actualizado con éxito'
        format.html { redirect_to action: "index" }
      else
        format.html { render :edit }
      end
    end
  end

  # DELETE /productos/1
  def destroy
    if @usuario == current_usuario
      flash[:error] = "No se puede eliminar a sí mismo"
      return redirect_to usuarios_path
    end
    @usuario.destroy
    respond_to do |format|
      format.html { redirect_to usuarios_path, notice: 'Usuario eliminado con éxito' }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_usuario
      @usuario = Usuario.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def usuario_params
      if params[:usuario][:password] == ""
        params[:usuario].delete :password
        params[:usuario].delete :password_confirmation
      end
      params.require(:usuario).permit(:email, :password, :password_confirmation, :nombre,
                                      :es_admin, :es_gestion, :es_consulta, :es_tags)
    end
end
