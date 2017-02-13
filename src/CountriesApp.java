import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class CountriesApp {
    static Scanner scan = new Scanner(System.in);

    public static void writeCountryToFile(String ListOfCountries, String countryInput) {
        Path filePath = Paths.get(ListOfCountries);
        File CountryList = filePath.toFile();

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(CountryList, true));
            out.print(countryInput);
            out.close();
        } catch (FileNotFoundException ex) {

        }
    }

    public static StringBuilder readCountriesFromFile(String ListOfCountries) {
        //Next to lines of code open a file to read
        Path productsPath = Paths.get(ListOfCountries);
        File productsFile = productsPath.toFile();

        StringBuilder result = new StringBuilder();
        try {
            FileReader r = new FileReader(productsFile);
            BufferedReader reader = new BufferedReader(r);

            String line = reader.readLine();
            while (line != null) {
                result.append(line + "\n");
                line = reader.readLine();
            }
            reader.close();
            return result;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void deleteCountry(String ListOfCountries, String userInputCountry) {
        Path productsPath = Paths.get(ListOfCountries);
        File productsFile = productsPath.toFile();
        Path filePath = Paths.get(ListOfCountries);
        File tempListofCountries = filePath.toFile();
        try {
            InputStream fis = new FileInputStream(tempListofCountries);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            PrintWriter out = new PrintWriter(new FileOutputStream(tempListofCountries, true));

            String currentLine;
            while((currentLine = br.readLine()) !=null){
                if(!currentLine.equalsIgnoreCase(userInputCountry)) {
                    out.println(currentLine);
                }
            }
            out.close();
            br.close();

            productsFile.delete();
            tempListofCountries.renameTo(productsFile);

        } catch (FileNotFoundException ex) {
            System.err.println("Error: Target File Cannot Be Read");
        }catch(IOException ex){

        }
    }


    public static void main(String[] args) {
        System.out.println("Welcome to the Countries List");
        int userChoice;
        do {
            System.out.println("1.Read the countries list\n2.Add to the list\n3.Delete form the list\n4.Exit");
            userChoice = Validator.getInt(scan, ("Choose 1-3: "));
            switch (userChoice) {
                case 1:
                    writeCountryToFile("ListOfCountries.txt", "India\nUnited States\nChina\nRussia \n");
                    StringBuilder readCountry = readCountriesFromFile("ListOfCountries.txt");
                    System.out.println(readCountry);
                    userChoice = 0;
                    break;
                case 2:
                    String newCountry = Validator.getString(scan, "Enter Country you want to add. ");
                    writeCountryToFile("ListOfCountries.txt", newCountry + "\n");
                    break;
                case 3:
                    String userDelete = Validator.getString(scan, "Enter Country you want to delete.");
                    deleteCountry("ListOfCountries.txt", userDelete);

                    break;

            }

        } while (userChoice != 4);
    }
}

