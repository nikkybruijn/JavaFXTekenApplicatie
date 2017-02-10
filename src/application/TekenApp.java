package application;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TekenApp extends Application {
	private InstellingenDialoog hetDialoog;
	private TekenCanvas hetCanvas;
	private LintBox hetLint;
	private File huidigBestand = null;
	private Stage refStage;
	
	BorderPane bpane = new BorderPane();

	public void start(Stage stage) {
		refStage = stage;
		VBox vb = new VBox();
		MenuBar mb = new MenuBar();
		Menu menuBestand = new Menu("_Bestand");
		MenuItem nieuwBestand = new MenuItem("_Nieuw");
		nieuwBestand.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		nieuwBestand.setOnAction(e -> nieuwBestandAanmaken());
		
		MenuItem menuOpslaan = new Menu("_Opslaan");
		menuOpslaan.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		menuOpslaan.setOnAction(e -> {
			afbeeldingOpslaan(false);
		});
		
		MenuItem menuOpslaanAls = new Menu("_Opslaan Als");
		menuOpslaanAls.setOnAction(e -> {
			afbeeldingOpslaan(true);
		});
		
		MenuItem menuAppSluiten = new MenuItem("Sluiten");
		menuAppSluiten.setOnAction(e -> {
			System.exit(0);
		});
		
		menuBestand.getItems().addAll(nieuwBestand, menuOpslaan, menuOpslaanAls, menuAppSluiten);
		
		Menu menuOpties = new Menu("_Opties");
		MenuItem menuInstellingen = new MenuItem("_Instellingen");
		menuInstellingen.setOnAction(e -> {
			hetDialoog.Melding();	
		});
		
		menuOpties.getItems().add(menuInstellingen);
		mb.getMenus().addAll(menuBestand, menuOpties);
		
		hetLint = new LintBox();
		hetLint.setPrefWidth(430);
		hetLint.setPrefHeight(50);
		hetCanvas = new TekenCanvas(hetLint, 430, 300);
		hetDialoog = new InstellingenDialoog(stage , 430, 300);
		vb.getChildren().addAll(mb, hetLint);
		bpane.setCenter(hetCanvas);
		bpane.setPrefSize(430, 300);
		bpane.setTop(vb);

		Scene scene = new Scene(bpane);
		stage.setScene(scene);
		stage.setTitle("TekenApp 2.0");
		stage.show();
	}
	
	private boolean afbeeldingOpslaan(boolean dialoogAltijdTonen) {
		boolean b = true;
		
		if (huidigBestand == null || dialoogAltijdTonen) {
		  FileChooser chooser = new FileChooser();
		    chooser.setTitle("Bestand opslaan");
		    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"));
		    chooser.setInitialFileName("Naamloos.png");
		    huidigBestand = chooser.showSaveDialog(refStage);
		    if (huidigBestand != null) {
		    	if (huidigBestand.getName().endsWith(".png")) {
		        try {
		        	SnapshotParameters params = new SnapshotParameters();
		        	WritableImage img = hetCanvas.snapshot(params, null);
		        	RenderedImage img2 = SwingFXUtils.fromFXImage(img, null);
		        	ImageIO.write(img2, "png", huidigBestand);
		        	b = true;
		        	
		        } catch (IOException ex) {
		             System.out.println(ex.getMessage());
		        }
		    	}
		    };
		}
		
		if (huidigBestand != null) {
        	SnapshotParameters params = new SnapshotParameters();
        	WritableImage img = hetCanvas.snapshot(params, null);
        	RenderedImage img2 = SwingFXUtils.fromFXImage(img, null);
        	try {
				ImageIO.write(img2, "png", huidigBestand);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public void nieuwBestandAanmaken() {
		hetCanvas = new TekenCanvas(hetLint, 430, 300);
		bpane.setCenter(hetCanvas);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
