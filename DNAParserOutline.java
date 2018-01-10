import java.lang.reflect.Array;
import java.util.Scanner;

public class DNAParserOutline {

    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) 
    {
        String input = "";
        String newInput = "";
        boolean inputCheck = true;
        boolean multipleThree = false;
        int indexStart=0;
        int indexEnd=0;
        int index =0;
        int current =0;
        double ARatio=0;
        double GRatio=0;
        double CRatio=0;
        double TRatio=0;

        System.out.print(("What is your DNA: "));
        newInput = scan.next().toUpperCase();


        String[] Dna = new String[newInput.length()];
        String[] Rna = new String[newInput.length()];

        while (inputCheck) 
        {
            for (int i = 0; i < newInput.length(); i++) 
            {

                Dna[i] = newInput.substring(i, i+1);

                if (!(Dna[i].equals("G") || Dna[i].equals("C") || Dna[i].equals("A") || Dna[i].equals("T"))) {
                    System.out.print("Enter valid DNA please");
                    newInput = scan.next();
                    break;
                }
            }

            if (inputCheck == false) 
                break;
            Rna = makeRNA(Dna);
            if (Rna == null) 
                break;
            indexStart = whereStart(Rna);
            if (indexStart == -1) 
                break;
            indexEnd = whereEnd(Rna, indexStart + 3);
            if (indexEnd == -1) 
                break;
            multipleThree = hasMultThree(indexStart, indexEnd);
            if (!multipleThree) 
                break;
            printAminoAcids(Rna, indexStart, indexEnd);

            while(!(input.equals("3"))) 
            {
            System.out.println("Press A to enter a new DNA sequence");
            System.out.println("Press B to check the percentages of each base pair");
            System.out.println("Press C to quit");

            input = scan.next();
            while(!(input.equals("A") || input.equals("B") || input.equals("C"))) 
            {
                System.out.print("Invalid. Enter again. ");
                input = scan.next();
            }

            if (input.equals("A")) 
            {
                System.out.print("Enter a difference DNA: ");
                input = scan.next().toUpperCase();


                while (index < newInput.length()) 
                {
                     index = newInput.indexOf(input, index);
                     if (current == 0 && index < 0) 
                     {
                            System.out.print("Your DNA sequence is invalid. Enter again. ");
                            newInput = scan.next().toUpperCase();
                     } 
                     else if (index > 0)
                     {
                            System.out.println("Your new DNA sequence was found at index " + index);
                            current++;
                     }
                     else if (current!=0 && index < 0) 
                            break;
                     index++;
                }
            }
            if (input.equals("B")) 
            {
                    ARatio = checkLetter(newInput, "A");
                    GRatio = checkLetter(newInput, "G");
                    CRatio = checkLetter(newInput, "C");
                    TRatio = checkLetter(newInput, "T");

                    System.out.println("The original sequence is " + ARatio + "% A");
                    System.out.println("The original sequence is " + GRatio + "% G");
                    System.out.println("The original sequence is " + CRatio + "% C");
                    System.out.println("The original sequence is " + TRatio + "% T");
            }
            }
            if (input.equals("C")) 
            {
                System.out.println("Bye.");
                inputCheck = false;
            }
        }
    }
    private static double checkLetter(String input, String letter) 
    {
        double index = 0;
        for(int i = 0; i < input.length(); i++) 
        {
            if(input.substring(i,i+1).equals(letter)) 
                index++;
        }
        return (index/input.length()*100);
    }
    public static String[] makeRNA(String[] Dna) 
    {
        String[] Rna = new String[Dna.length];
        for (int x = 0; x < Dna.length; x++) 
        {
            if (Dna[x].equals("A")) 
                Rna[x] = "U";

            else if (Dna[x].equals("T")) 
                Rna[x] = "A";

            else if (Dna[x].equals("G")) 
                Rna[x] = "C";

            else if (Dna[x].equals("C")) 
                Rna[x] = "G";
        }
        return Rna;
    }

    public static int whereStart(String[] Rna) 
    {
        for (int i = 0; i < Rna.length; i++) 
        {
            if (Rna[i].equals("A") && Rna[i + 1].equals("U") && Rna[i + 2].equals("G"))
                return i;
        }
        return -1;
    }

    public static int whereEnd(String[] Rna, int indexStart) 
    {
        while (indexStart < Rna.length) {

            if (Rna[indexStart].equals("U") && Rna[indexStart + 1].equals("A") && Rna[indexStart + 2].equals("G"))
                return indexStart + 2;

            else if (Rna[indexStart].equals("U") && Rna[indexStart + 1].equals("A") && Rna[indexStart + 2].equals("A"))
                return indexStart + 2;

            else if (Rna[indexStart].equals("U") && Rna[indexStart + 1].equals("G") && Rna[indexStart + 2].equals("A"))
                return indexStart + 2;
            indexStart++;
        }
        return -1;
    }

    public static boolean hasMultThree(int start, int end) 
    {
        if (((end - start + 1) % 3 == 0)) 
            return true;
        else 
            return false;
    }

    public static void printAminoAcids(String[] Rna, int start, int end)
    {
        String response = "";
        for(int i=0; i<end; i=i+3)
        {
            if (Rna[i].equals("A"))
            {
                if(Rna[i+1].equals("G"))
                {
                    if(Rna[i+2].equals("G") || Rna[i+2].equals("A"))
                        response = "Arginine";
                    else
                        response = "Serine";
                }
                if(Rna[i+1].equals("A"))
                {
                   if(Rna[i+2].equals("G") || Rna[i+2].equals("A"))
                        response = "Lysine";
                   else 
                        response = "Asparagine";
                }
                if(Rna[i+1].equals("C"))
                    response = "Threorine";
                if(Rna[i+1].equals("U"))
                {
                   if(Rna[i+2].equals("G"))
                        response = "Methionine";
                   else 
                        response = "Isoleucine";
                }
            }
            
            if (Rna[i].equals("G"))
            {
                if(Rna[i+1].equals("G"))
                    response = "Glycine";
                if(Rna[i+1].equals("A"))
                {
                    if(Rna[i+2].equals("G") || Rna[i+2].equals("A"))
                        response = "Glutamate";
                    else
                        response = "Aspartate";
                }
                if(Rna[i].equals("C"))
                    response = "Alanine";
                if(Rna[i].equals("U"))
                    response = "Valine";
            }
            
            if(Rna[i].equals("U"))
            {
                if(Rna[i+1].equals("C"))
                    response = "Serine";
                if(Rna[i+1].equals("U"))
                    if(Rna[i+2].equals("U") || Rna[i+2].equals("C"))
                        response = "Phenylatanine";
                    else 
                        response = "Leucine";
                if(Rna[i+1].equals("A"))
                    if(Rna[i+2].equals("U") || Rna[i+2].equals("C"))
                        response = "Tyrosine";
                    else 
                        response = "Stop";
                if(Rna[i+1].equals("G"))
                    if(Rna[i+2].equals("U") || Rna[i+2].equals("C"))
                        response = "Cysteine";
                    else if(Rna[i+2].equals("G"))
                        response = "Tryptophane";
                    else
                        response = "Stop";
            }
            
            if(Rna[i].equals("C"))
            {
                if(Rna[i+1].equals("U"))
                    response = "Leucine";
                if(Rna[i+1].equals("C"))
                    response = "Proline";
                if(Rna[i+1].equals("A"))
                    if(Rna[i+2].equals("U") || Rna[i+2].equals("C"))
                        response = "Histidine";
                    else 
                        response = "Glutamine";
                if(Rna[i+1].equals("G"))
                    response = "Arginine";
            }
            
            System.out.print(Rna[i] + Rna[i+1] + Rna[i+2] + " ");
            System.out.println(response + ", ");
        }
    }
}

