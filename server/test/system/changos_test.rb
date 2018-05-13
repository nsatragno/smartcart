require "application_system_test_case"

class ChangosTest < ApplicationSystemTestCase
  setup do
    @chango = changos(:one)
  end

  test "visiting the index" do
    visit changos_url
    assert_selector "h1", text: "Changos"
  end

  test "creating a Chango" do
    visit changos_url
    click_on "New Chango"

    fill_in "Codigo", with: @chango.codigo
    click_on "Create Chango"

    assert_text "Chango was successfully created"
    click_on "Back"
  end

  test "updating a Chango" do
    visit changos_url
    click_on "Edit", match: :first

    fill_in "Codigo", with: @chango.codigo
    click_on "Update Chango"

    assert_text "Chango was successfully updated"
    click_on "Back"
  end

  test "destroying a Chango" do
    visit changos_url
    page.accept_confirm do
      click_on "Destroy", match: :first
    end

    assert_text "Chango was successfully destroyed"
  end
end
