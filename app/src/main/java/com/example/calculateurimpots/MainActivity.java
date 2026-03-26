package com.example.calculateurimpots;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclaration des nouveaux champs Nom et Adresse
    private EditText nomInput, adresseInput; 
    private EditText surfaceInput, piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des nouveaux champs avec le XML
        nomInput = findViewById(R.id.input_nom); // Récupère le champ Nom
        adresseInput = findViewById(R.id.input_adresse); // Récupère le champ Adresse
        
        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);

        findViewById(R.id.button_calcul).setOnClickListener(v -> calculer());
    }

    private void calculer() {
        // Récupération des valeurs textuelles
        String nom = nomInput.getText().toString();
        String adresse = adresseInput.getText().toString();
        String surfaceStr = surfaceInput.getText().toString();
        String piecesStr = piecesInput.getText().toString();

        // Vérification si tous les champs sont remplis
        if (nom.isEmpty() || adresse.isEmpty() || surfaceStr.isEmpty() || piecesStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double surface = Double.parseDouble(surfaceStr);
            int pieces = Integer.parseInt(piecesStr);
            boolean piscine = piscineCheckbox.isChecked();

            // Calcul des impôts
            double impotBase = surface * 2;
            double supplement = (pieces * 50) + (piscine ? 100 : 0);
            double total = impotBase + supplement;

            // Affichage du résultat détaillé avec le nom et l'adresse
            String resultat = getString(R.string.resultat_format, 
                    nom, adresse, impotBase, supplement, total);
            
            resultView.setText(resultat);
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer des nombres valides pour la surface et les pièces", Toast.LENGTH_SHORT).show();
        }
    }
}
