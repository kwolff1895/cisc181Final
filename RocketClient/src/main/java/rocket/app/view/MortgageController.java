package rocket.app.view;

import java.text.NumberFormat;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	private static NumberFormat cf = NumberFormat.getCurrencyInstance();
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtDownPayment;
	
	@FXML
	private TextField txtExpenses;
	
	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private ComboBox<String> cmbTerm;
	
ObservableList<String> termList = FXCollections.observableArrayList("15 Years", "30 Years") ;
	//add terms to combobox
	@FXML
	private void initialize() {
		cmbTerm.setValue(termList.get(0));
		cmbTerm.setItems(termList);
		}	
	
	@FXML 
	private Label lblMortgagePayment;
	
	@FXML
	private Button LoanPaymentCalculator;
	
	@FXML
	private Label CreditScore;
	
	@FXML
	private Label Term;
	
	@FXML
	private Label Income;
	
	@FXML
	private Label Expenses;
	
	@FXML
	private Label HouseCost;
	
	@FXML
	private Label DownPayment;
	
	@FXML 
	private Label ExceptionThrown; 
	
	@FXML
	private Label PaymentException;
	
	@FXML
	private Button Exit;
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		//House Cost
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		//Down Payment
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		//Expenses
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		//Credit Score
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		//Rate
		try {
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
		} catch (RateException e) {
			lq.setdRate(-1);
			ExceptionThrown.setText("Invalid Credit Score");
		}
		//Term
		if(cmbTerm.getValue() == "15 Years"){
			lq.setiTerm(15);
		}
		else{
			lq.setiTerm(30);
		}

		a.setLoanRequest(lq);
		
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
	double pmt = lRequest.getdPayment();
	double PITI;
	double PITI_one = (lRequest.getIncome()*.28);
	double PITI_two = ((lRequest.getIncome()*.36)- lRequest.getExpenses());
	
	if (PITI_one < PITI_two){
		PITI = PITI_one;
	}
	else{
		PITI=PITI_two;
	}
	
	//Exception:Tells the user that that the payment exceeds their maximum monthly payments
if (pmt>PITI){
	PaymentException.setText(pmt + PITI + "Payment exceeds maximum monthly payments");
	
}
//Displays the Mortgage Payment 
else{
	 lblMortgagePayment.setText(cf.format(lRequest.getdPayment()));
}
}
	@FXML
	public void Exit(ActionEvent event)
	{
		try {
			mainApp.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}