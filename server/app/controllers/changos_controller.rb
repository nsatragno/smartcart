class ChangosController < ApplicationController
  before_action :authenticate_usuario!, except: [ :insertar_tag, :remover_tag ]
  before_action :validar_gestion, except: [ :insertar_tag, :remover_tag ]
  before_action :set_chango, only: [
    :show, :edit, :update, :destroy, :qr, :insertar_tag, :remover_tag
  ]
  before_action :set_rfid, only: [ :insertar_tag, :remover_tag ]

  # GET /changos
  # GET /changos.json
  def index
    @changos = Chango.all
  end

  # GET /changos/1.json
  def show
  end

  # GET /changos/new
  def new
    @chango = Chango.new
  end

  # GET /changos/1/edit
  def edit
  end

  # POST /changos
  # POST /changos.json
  def create
    @chango = Chango.new(chango_params)

    respond_to do |format|
      if @chango.save
        flash[:success] = 'Chango creado con éxito'
        format.html { redirect_to action: "index" }
      else
        format.html { render :new }
        format.json { render json: @chango.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /changos/1
  # PATCH/PUT /changos/1.json
  def update
    respond_to do |format|
      if @chango.update(chango_params)
        flash[:success] = 'Chango actualizado con éxito'
        format.html { redirect_to action: "index" }
        format.json { render :show, status: :ok, location: @chango }
      else
        format.html { render :edit }
        format.json { render json: @chango.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /changos/1
  # DELETE /changos/1.json
  def destroy
    @chango.destroy
    flash[:success] = 'Chango eliminado con éxito'
    respond_to do |format|
      format.html { redirect_to changos_url }
      format.json { head :no_content }
    end
  end

  def insertar_tag
    @chango.tags << @tag
    @chango.save!
    flash[:success] = 'Tag insertado con éxito'
    respond_to do |format|
      format.html { render :edit }
      format.json { render :show, status: :ok, location: @chango }
    end
  end

  def remover_tag
    @chango.tags.delete @tag
    @chango.save!
    flash[:success] = 'Tag removido con éxito'
    respond_to do |format|
      format.html { render :edit }
      format.json { render :show, status: :ok, location: @chango }
    end
  end

  def qr
    qr = RQRCode::QRCode.new "smartcart://open.chango/#{@chango.id}"
    send_data qr.as_png(size: 300).to_s, type: "image/png", disposition: "inline"
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_chango
      @chango = Chango.find(params[:id])
    end

    def set_rfid
      @tag = Tag.find_by_rfid params[:rfid]
      raise ActionController::RoutingError.new('RFID no encontrado') unless @tag
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def chango_params
      params.require(:chango).permit(:codigo)
    end
end
