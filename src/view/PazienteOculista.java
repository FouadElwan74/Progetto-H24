package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DataBase.PazienteDAOimp;
import dominio.Paziente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class PazienteOculista extends JFrame {
    private PazienteDAOimp pazienteDAO = new PazienteDAOimp("oculista"); 
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelMain = new JPanel(null);
    private JButton buttonAddRow = new JButton("ADD RIGA");
    private JButton buttonDeleteRow = new JButton("DELETE");
    private JButton buttonAddToDB = new JButton("ADD");
    JLabel PazienteOcchi = new JLabel("*PAZIENTE*");

    public PazienteOculista() {
        this.setTitle("Paziente Oculista");
        this.setSize(800, 770);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(panelMain);
        
        String[] columnNames = {"COGNOME", "NOME", "DATA_DI_NASCITA", "CODICE_FISCALE", "NOME_DOTTORE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 70, 800, 610);
        
        PazienteOcchi.setFont(new Font("Traditional Arabic", Font.BOLD, 40));
        buttonAddRow.setBounds(10, 700, 150, 40);
        buttonDeleteRow.setBounds(650, 700, 150, 40);
        buttonAddToDB.setBounds(325, 700, 150, 40);
        PazienteOcchi.setBounds(0, 30, 600, 50);

        panelMain.setBackground(Color.decode("#89CFF0"));
        panelMain.add(scrollPane);
        panelMain.add(buttonAddRow);
        panelMain.add(buttonDeleteRow);
        panelMain.add(buttonAddToDB);
        panelMain.add(PazienteOcchi);
        
        setButtonStyle(buttonAddRow);
        setButtonStyle(buttonDeleteRow);
        setButtonStyle(buttonAddToDB);

        buttonAddRow.addActionListener(this::addEmptyRow);
        buttonDeleteRow.addActionListener(this::deleteSelectedRows);
        buttonAddToDB.addActionListener(this::saveLastRow);

        loadDataFromDatabase();
    }

    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Congenial black", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
    }

    private void addEmptyRow(ActionEvent e) {
        tableModel.addRow(new Object[]{"", "", "", "", ""});
    }

    private void deleteSelectedRows(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String codiceFiscale = (String) tableModel.getValueAt(selectedRow, 3);
            try {
                pazienteDAO.deletePaziente(codiceFiscale);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Riga eliminata con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione della riga.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveLastRow(ActionEvent e) {
        int lastRowIndex = tableModel.getRowCount() - 1;

        if (lastRowIndex >= 0) {
            String cognome = (String) tableModel.getValueAt(lastRowIndex, 0);
            String nome = (String) tableModel.getValueAt(lastRowIndex, 1);
            String dataDiNascita = (String) tableModel.getValueAt(lastRowIndex, 2);
            String codiceFiscale = (String) tableModel.getValueAt(lastRowIndex, 3);
            String nomeDottore = (String) tableModel.getValueAt(lastRowIndex, 4);

            if (validateRow(cognome, nome, dataDiNascita, codiceFiscale, nomeDottore)) {
                Paziente paziente = new Paziente(cognome, nome, dataDiNascita, codiceFiscale, nomeDottore);
                try {
                    pazienteDAO.addPaziente(paziente);
                    JOptionPane.showMessageDialog(this, "Record aggiunto con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tutti i campi devono essere compilati.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nessun record da aggiungere", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRow(String cognome, String nome, String dataDiNascita, String codiceFiscale, String nomeDottore) {
        return !cognome.trim().isEmpty() && !nome.trim().isEmpty() && !dataDiNascita.trim().isEmpty() && !codiceFiscale.trim().isEmpty() && !nomeDottore.trim().isEmpty();
    }

    private void loadDataFromDatabase() {
        try {
            List<Paziente> pazienti = pazienteDAO.getAllPazienti();
            tableModel.setRowCount(0);

            for (Paziente paziente : pazienti) {
                tableModel.addRow(new Object[]{
                        paziente.getCognome(),
                        paziente.getNome(),
                        paziente.getDataDiNascita(),
                        paziente.getCodiceFiscale(),
                        paziente.getNomeDottore()
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new PazienteOculista();
        frame.setVisible(true);
    }

    public JPanel getPanelA() {
        return panelMain;
    }
}