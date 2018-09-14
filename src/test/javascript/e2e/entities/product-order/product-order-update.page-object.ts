import { element, by, ElementFinder } from 'protractor';

export default class ProductOrderUpdatePage {
  pageTitle: ElementFinder = element(by.id('eCommerceApp.productOrder.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  placedDateInput: ElementFinder = element(by.css('input#product-order-placedDate'));
  statusSelect: ElementFinder = element(by.css('select#product-order-status'));
  codeInput: ElementFinder = element(by.css('input#product-order-code'));
  customerSelect: ElementFinder = element(by.css('select#product-order-customer'));

  getPageTitle() {
    return this.pageTitle;
  }

  setPlacedDateInput(placedDate) {
    this.placedDateInput.sendKeys(placedDate);
  }

  getPlacedDateInput() {
    return this.placedDateInput.getAttribute('value');
  }

  setStatusSelect(status) {
    this.statusSelect.sendKeys(status);
  }

  getStatusSelect() {
    return this.statusSelect.element(by.css('option:checked')).getText();
  }

  statusSelectLastOption() {
    this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }
  setCodeInput(code) {
    this.codeInput.sendKeys(code);
  }

  getCodeInput() {
    return this.codeInput.getAttribute('value');
  }

  customerSelectLastOption() {
    this.customerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  customerSelectOption(option) {
    this.customerSelect.sendKeys(option);
  }

  getCustomerSelect() {
    return this.customerSelect;
  }

  getCustomerSelectedOption() {
    return this.customerSelect.element(by.css('option:checked')).getText();
  }

  save() {
    return this.saveButton.click();
  }

  cancel() {
    this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }
}
