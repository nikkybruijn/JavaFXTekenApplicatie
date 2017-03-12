package application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InstellingenDialoog extends Stage {
	private Stage refStage;
	TextField topTextField = new TextField();
	TextField bottomTextField = new TextField();
	Button cancelButton = new Button("Annuleren");
	Button acceptButton = new Button("Ok");
	
	public InstellingenDialoog(Stage owner) {
		super(StageStyle.UTILITY);
		refStage = owner;
		initOwner(owner);
		initModality(Modality.WINDOW_MODAL);
		this.setResizable(false);
		HBox hbox = new HBox(5);
		
		Label topLabel = new Label("Hoogte:");
		topLabel.setPrefWidth(250);
		Label bottomLabel = new Label("Breedte:");
		bottomLabel.setPrefWidth(250);
		
		cancelButton.setOnAction(e -> this.hide());
		
		acceptButton.setOnAction(e -> this.hide());
		acceptButton.setPrefWidth(75);
		
		VBox controlBox = new VBox(15);
		hbox.getChildren().addAll(cancelButton, acceptButton);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		controlBox.setAlignment(Pos.CENTER);
		controlBox.getChildren().addAll(topLabel, topTextField, bottomLabel, bottomTextField, hbox);
		controlBox.setPadding(new Insets(15));

		Scene scene = new Scene(controlBox);
		this.setScene(scene);
	}
	
	public void Melding() {
		InstellingenDialoog al = new InstellingenDialoog(refStage);
		al.showAndWait();
	}

}
