require 'test_helper'

class ChangosControllerTest < ActionDispatch::IntegrationTest
  setup do
    @chango = changos(:one)
  end

  test "should get index" do
    get changos_url
    assert_response :success
  end

  test "should get new" do
    get new_chango_url
    assert_response :success
  end

  test "should create chango" do
    assert_difference('Chango.count') do
      post changos_url, params: { chango: { codigo: @chango.codigo } }
    end

    assert_redirected_to chango_url(Chango.last)
  end

  test "should show chango" do
    get chango_url(@chango)
    assert_response :success
  end

  test "should get edit" do
    get edit_chango_url(@chango)
    assert_response :success
  end

  test "should update chango" do
    patch chango_url(@chango), params: { chango: { codigo: @chango.codigo } }
    assert_redirected_to chango_url(@chango)
  end

  test "should destroy chango" do
    assert_difference('Chango.count', -1) do
      delete chango_url(@chango)
    end

    assert_redirected_to changos_url
  end
end
