import java.io.IOException;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

class Main {

   public static void main(String[] args) {
      
      String file = "./results.csv";
      Path filePath = Paths.get(file);

      Pattern pat = Pattern.compile("^([\\d-]{10}),(.+),(.+),(\\d+),(\\d+),Friendly,.*$$");

      try(Stream<String> stream = Files.lines(filePath)) {

         // This type of integer assures integrity in a concurrent operation
         AtomicInteger match = new AtomicInteger();
         AtomicInteger noMatch = new AtomicInteger();

         // If we want a concurrent mode add .parallel() between stream and .forEach
         stream.forEach( line -> {
            Matcher matcher = pat.matcher(line);
            if(matcher.find()) {
               match.getAndIncrement();
               System.out.println(matcher.group(1)+" : "+matcher.group(2)+" ["+matcher.group(4)+" "+matcher.group(5)+"] "+matcher.group(3));
            } else {
               noMatch.getAndIncrement();
            }
         });

         System.out.println("No. de matches: "+match+"\nNo. de no matches: "+noMatch);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

}