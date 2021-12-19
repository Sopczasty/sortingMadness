package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class responsible for sorting objects.
 * Prints out initial data and after sorting
 * prints sorted data in specified order.
 */
public class SortingMadness {

    private static int [] input = {359,267,734,325,200,135,529,680,659,711,760,615,696,260,786,229,325,352,397,752,460,529,726,13,727,728,795,401,175,663,327,767,407,541,243,254,33,594,772,700,33,250,585,530,763,191,344,754,441,197,507,708,390,246,618,333,760,6,504,672,63,417,200,788,273,338,129,103,298,355,781,5,522,482,152,357,535,661,193,424,698,710,508,143,722,677,268,308,655,671,536,73,220,627,415,465,707,406,215,16,422,470,77,588,363,106,654,633,217,340,546,741,396,791,595,752,563,481,676,359,9,755,503,205,718,750,442,219,765,111,327,84,651,40,727,637,549,652,371,720,244,636,643,607,732,81,515,662,227,715,672,43,185,774,631,571,16,260,437,377,132,185,334,689,443,279,687,740,447,610,334,222,214,240,79,273,649,623,642,685,724,287,627,652,792,796,524,469,586,379,53,316,6,450,583,789,290,379,583,710,381,537,798,115,79,423,413,477,449,420,104,729,482,691,74,798,637,485,439,701,156,68,60,26,31,392,649,379,307,206,62,13,422,643,134,12,352,607,751,292,416,563,247,769,133,288,767,377,503,763,255,704,572,109,486,83,643,709,638,56,387,719,374,46,613,35,491,663,593,296,119,564,500,732,497,384,35,52,664,513,711,495,496,114,690,344,773,19,172,134,411,41,250,609,632,310,670,500,95,119,173,69,524,386,130,707,2,554,724,50,226,643,454,282,786,471,168,300,119,282,83,260,314,422,289,359,302,601,460,71,479,441,491,431,110,213,751,165,344,178,642,103,473,584,390,541,222,198,52,256,639,503,83,770,214,351,779,509,401,197,711,132,427,473,385,199,311,38,123,638,261,87,657,201,115,277,746,535,44,499,56,22,660,679,109,427,690,434,789,54,456,374,669,497,157,221,19,194,427,113,439,714,685,43,271,304,792,144,572,260,773,747,107,747,736,148,782,682,450,510,376,775,367,127,173,585,419,468,100,504,285,180,613,101,795,474,667,494,427,604,157,219,446,729,533,6,751,723,330,98,455,572,349,280,390,164,128,710,207,159,271,615,81,525,750,798,750,72,35,697,69,666,496,671,565,38,374,278,669,761,548,713,327,353,720,480,431,217,111,767,317,466,272,541,541,98,316,162,594,488,385,1,492,306,661,129,262,266,633,297,187,219,142,282,63,660,268,618,241,794,214,159,636,319,104,455,31,579,150,313,264,150,503,68,372,240,622,399,482,539,87,611,74,627,606,355,721,530,131,168,454,750,435,308,374,657,643,494,127,496,300,348,205,184,712,579,614,25,339,454,164,675,32,584,499,490,493,611,4,158,368,690,756,442,387,650,45,531,665,114,543,452,511,261,157,779,170,316,438,509,131,303,96,564,517,137,504,339,584,727,540,148,687,17,532,528,314,15,736,18,512,4,491,245,214,785,400,381,573,592,250,87,616,305,210,5,631,702,606,60,690,82,279,725,528,649,737,29,408,344,740,179,60,140,329,543,40,749,117,333,712,737,733,573,519,602,159,620,8,8,463,47,191,151,543,103,300,535,556,712,570,91,230,249,487,689,635,399,549,229,616,127,507,314,480,639,67,742,164,19,179,485,771,753,286,633,215,727,776,429,387,618,425,45,235,582,403,729,634,475,635,754,683,418,203,116,172,32,119,384,39,311,436,2,590,474,271,418,198,211,764,476,749,146,58,397,588,412,196,695,371,514,709,185,645,429,766,795,246,471,749,120,528,472,550,517,631,636,140,219,389,519,6,661,192,618,251,483,650,573,579,727,51,566,381,232,310,88,9,324,766,129,366,440,764,330,100,649,396,218,488,514,453,587,673,365,683,4,506,768,320,759,756,89,507,331,420,771,334,783,40,569,563,272,354,692,195,529,455,706,767,416,756,515,39,93,738,232,121,444,732,386,154,227,433,757,296,93,217,433,254,265,706,361,393,176,442,778,375,747,144,64,567,153,511,580,712,798,44,23,767,534,613,30,437,108,489,660,260,159,557,78,502,618,567,790,384,611,411,695,187,106,764,634,176,714,138,399,500,288,584,504,361,94,13,18,653,641,730,796,18,753,220,226,770,791,46,706,716,209,174,158,769,606,365,389,209,95,737,551,157,495,522,634,762,693,682,273,264,763,270,753,68,335,191,701,627,428,693,401,580,253,654,252,392,482,179,506,778,206,752,180,457,546,726,74,377,343,545,535,218,669,416,394,776,9,80,437,670,598,383,235,146,82,629,257,320,532,356,388,618,18,196,399,159,33,404,385,570,109,360,531,114,765,382,677,749,425,259,776,751,255,629,497,101,467,112,78,600,798,488,492,577,4,658,276,476,551,74,684,346,54,291,24,578,87,708,268,756,556,506,263,393,204,467,32,353,578,291,739,791,267,337,191,99,425,606,603,46,79,371,593,647,428,638,478,380,420,90,544,750,662,210,470,646,548,329,319,12,157,374,214,170,218,436,375,451,237,685,44,696,731,648,49,383,634,340,303,161,764,535,736,15,644,117,589,563,570,173,88,554,351,570,364,557,267,337,354,568,600,356,551,163,721,256,174,363,291,191,363,78,443,231,112,545,490,526,520,645,220,687,673,325,588,316,481,426,384,498,526,23,660,516,371,103,691,138,419,554,544,33,23,433,247,111,530,678,424,413,696,753,307,199,272,433,678,131,753,799,483,244,360,44,227,179,680,684,592,600,153,355,795,779,265,27,565,358,435,12,658,639,682,634,546,381,330,668,745,560,201,258,38,116,760,484,677,567,390,260,408,54,63,713,374,578,636,96,678,116,530,565,789,573,431,204,171,628,500,108,116,554,510,468,185,699,528,264,559,460,158,723,426,636,334,147,493,744,566,590,131,23,637,695,131,121,93,354,482,164,50,614,336,42,583,455,667,6,103,502,187,279,703,44,440,685,195,25,715,173,318,87,529,489,706,526,272,252,415,84,104,471,130,691,575,684,88,283,552,557,97,210,397,81,169,20,569,494,284,331,182,324,144,763,394,677,655,4,764,237,98,404,403,428,510,766,128,634,565,409,263,632,126,137,622,303,528,530,31,440,323,423,796,89,96,89,191,660,368,664,113,732,172,200,108,512,652,293,192,299,746,561,94,523,174,158,732,380,326,49,656,241,512,470,340,558,407,302,412,36,760,415,684,219,520,661,372,548,767,480,428,323,536,302,450,610,373,364,443,338,197,28,226,111,740,410,312,559,662,188,210,444,537,45,372,321,672,255,16,359,630,317,330,561,309,779,523,244,766,351,313,446,735,279,735,474,31,386,575,314,539,26,577,22,713,645,684,469,385,237,541,339,657,658,746,547,321,538,491,462,625,129,585,382,249,657,641,52,769,728,751,711,459,426,343,476,409,695,739,213,539,635,295,161,107,283,86,190,516,277,206,714,456,357,464,1,129,139,755,442,241,744,431,82,547,796,510,90,275,222,567,605,175,46,48,471,292,297,769,465,395,622,790,625,204,489,355,264,75,132,327,696,286,324,193,609,439,120,591,773,514,616,341,628,37,756,151,790,296,218,40,195,288,33,401,654,116,449,547,90,35,435,412,566,98,173,352,526,419,786,482,780,62,493,399,145,513,634,754,72,465,556,263,334,360,122,547,729,105,558,472,326,615,375,522,687,72,151,675,100,562,239,507,766,471,396,528,9,548,342,83,522,638,364,202,731,54,81,118,675,80,335,183,370,231,541,602,672,62,232,753,141,776,437,89,738,677,440,459,257,248,199,311,510,594,530,2,786,107,276,577,300,692,383,471,796,762,617,360,783,563,614,22,631,587,403,423,395,330,550,130,519,560,153,569,347,519,292,132,353,511,587,574,70,395,212,247,682,144,410,607,79,728,251,646,255,337,738,724,569,400,272,598,581,27,754,599,487,599,263,89,456,710,783,110,542,341,729,296,577,90,232,253,432,742,435,670,706,527,198,422,79,688,397,364,466,403,200,681,132,114,690,702,213,173,403,180,208,458,429,165,538,715,742,264,674,550,267,91,48,213,713,505,115,761,683,178,269,152,563,193,202,770,466,571,551,790,108,381,481,690,82,228,491,703,666,460,800,170,82,410,58,250,649,401,734,585,534,775,215,295,151,307,332,650,572,490,438,114,608,169,742,202,73,180,552,650,640,71,750,177,670,68,322,487,650,356,733,637,401,669,474,181,594,152,220,459,220,52,770,198,166,792,116,34,800,278,204,273,37,178,10,629,162,23,74,684,242,254,278,591,397,296,651,38,135,708,475,427,169,200,449,73,129,126,82,340,469,338,8,653,209,709,152,637,637,317,13,16,444,234,314,47,407,158,695,154,245,173,155,455,563,59,665,541,229,742,104,350,623,334,240,90,529,506,614,661,162,293,180,314,421,628,216,92,766,421,655,398,561,576,333,12,775,27,643,590,683,186,607,418,444,783,723,745,94,510,449,525,606,605,744,156,709,219,21,636,125,220,102,739,789,466,241,179,678,612,667,724,281,449,471,387,355,743,213,740,539,638,799,580,32,540,348,576,629,78,10,716,591,33};
    private static String direction = "";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    private static String sorting_type = "bubble";


    public static void main(String[] args) {

        logger.info("Initializing sorter of selected type.");

        // Error handling
        if (input.length == 0) {
            logger.debug("Input data is empty. Returning.");
            System.out.println("Input data is empty.");
            return;
        }

        //Example setup
        //sorter = wrapper.getSorter("bubble");
        sorter = wrapper.getSorter(input);
        System.out.println(sorter.getName());

        logger.info("Selected type: " + sorter.getName());

        System.out.println("Zestaw danych:");
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println("");

        logger.info("Initializing sorting.");

        /* Handling for sorting without specified order and with specified order
        Error handling for incorrect order */
        if(direction == null || direction.equals("")){
            timer.startMeasure();
            logger.debug("Direction undefined - initializing with input only");
            input = sorter.sort(input);
            timer.stopMeasure();
        }
        else if(direction.equals("asc") || direction.equals("desc")){
            timer.startMeasure();
            input = sorter.sort(input, direction);
            timer.stopMeasure();
        }
        else{
            logger.info("Input order is incorrect. Returning.");
            System.out.println("Input order is incorrect.");
            return;
        }
          
        // Printing result
        System.out.println("Posortowane dane:");
        for (int i : input) {
            System.out.print(i + " ");
        }

        // Timing
        System.out.println("");
        System.out.println("Time elapsed: " + timer.getLastMeasure() + "ms");
    }
}
