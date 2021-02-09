package com.phenix.adobepremiereproject;

import com.phenix.adobepremiereproject.AdobeTitle.AdobeTitle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * TODO : a supprimer
 *
 * @author Edouard
 */
public class Base65 {

    public static void main(String[] args) throws IOException, DataFormatException, ParserConfigurationException, SAXException {

        /*ByteArrayOutputStream xmlout = new ByteArrayOutputStream(10000); // ADD

        String inputString = "blahblahblah5tr4rfh5yg4j55hygf56h4dfh65";
        byte[] input = inputString.getBytes("UTF-8");
        System.out.println("original: " + inputString); // AD
        // Compress the bytes
        byte[] output = new byte[100];
        Deflater compresser = new Deflater();
        compresser.setInput(input);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        xmlout.write(output, 0, compressedDataLength); // ADD
        compresser.end();

        System.out.println("compresse: " + Arrays.toString(xmlout.toByteArray()) + " (size: " + xmlout.toByteArray().length + ")"); // AD

        xmlout = new ByteArrayOutputStream(10000); // ADD

        // Decompress the bytes
        Inflater decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[100];
        int resultLength = decompresser.inflate(result);
        xmlout.write(result);// ADD
        decompresser.end();
        System.out.println("decompresse: " + new String(xmlout.toByteArray()).toString()); // AD
         */
        try {
            String data = "AQAAAAAAAACe3gAAAAAAAENvbXByZXNzZWRUaXRsZQB4nO0da1MbOVI/xXXfA34ANlVzbBECu9SSkMJsEj6lzDPec2yfbRK4H393/ZBG7xnzCDNOVBTg6W5JLalf0z0e/e+/mfhN3ImvYiQa4pu4EjMxF0MxEWPxT/EP0RJrogn/G4AZiwuAXwJ2LG4I+5c4FQfiFVBtEc1vYkdkYhdoJuIcWnwWJ/BpIhYe/BR6WcCYV4T5YI27I9owZlP04LcLv22gWPdoMvGWeubrz6IP+AVdI3dzongPIwzEPWAPgGomvsPVDLjYAbqZuIUW2HOcKoNeF3S1AKwa5wKur2g9dsQ1YEcwGvdURo0czfJ1GcHPDsyPeXDhmdiHvuYEPaTWTOlCkW4M3OKI1/BTzGExLfI3AdpFkEMfY/J4DOu5CHCp4Agt37H1AinB+c6J3yHhZ+KI9m1CI8xB/j5E5Rfbm9Tu9T6txxX0vqBxrgEe6q8t+zLpT6GXKfGo5h7CMf+oPRferpgY5GsI6zGA1re0N3OPPkSRiT9oJbGfN4Qf0qq4bWNU3G/xirirhj18JdlRa1S0ZlPxCaToEP7uQy99gLXENuk6ju1jscUZfD4U7wDG9GwVmN7GIXVf7IF07sPPO5CjE2rBtD4mg7nPgMfvMI8vNJOZnK2yMAp/Quv0heaL12Pqs6y1u57+WrmruUvawNKN12pf+0CDO6G1YZdGGxAWr6+l5q3J+WpYBn8nMH8TqyAZjcUzY/w24U1oBrxMiK8JcG9S2XC2fkV8KuyusUrxeTTFZuFMFL5oLptLzmazZD4xjteX2r3XQHMh/iXl5ZZ0L6QlG1JL+sDrAGgQf0Kzv6Ix0fuiHdkArrdBC5jDYtoMrK/vA46l7E0BirZAWeATuMb/2HJMnvArtTNnydoUb4f40IwR84Y4HABkBG3RV97ntr1spuHZrRf2aeNO4eqO9PAq9yJTS1+fh4fycX4H2ABgX8jiH4O8/C1th90Ty88Vrd8etdDeZC7+DVQD4gV5CFNl0POEdknB9qDnCUkC7oneH+yjmNbv65j+jkv70XSo5Yi5Eh/h/yXZzp1c031MBhI4JF2akc/jK9UijMvAJrOHew24/8Bn1jRXC2JUmTGbPdKCMeEOgGZEssU704HYdA04aTzTJ7ZVy4ycgayMSKqmtDcvzeXyoyP1w6Rdac8eWTbcoRCVaZ2U7xjkdyYqDnOhbP3vSEvGxBf6b1cuwjQoVRO6K/go52PfQ/jYjLQH7SCu0pGU7jnEN+6IcbpYH2dL9nEm77tG5APHMv5ALVF+lSNuH5/Rncw5RezoX0zL+lHOlf1vjCojnZxS3xekm6dE2yf/cZHvSovu9bCv5ehj/WqrZ86tjDaju7WRt54KqtZBSbN95cohy867FZdOs+XjZLO8hySZP1Yyw5J4nu9GPWTU9chJSn81KY3J41yOOpajrJ58Jh//c8ioK4nrS8aoxfeB4WylygEUt20E7kS5Nd6rbuV98Pz79PdCatUQ7nWu6L5gS+6IhuE63Ik/4LPOmOid83FM/yfxofPTNgw1kjPBag37dCegMj2sj3GK5TUfx+3LO2DcgW6eIbLhLmUoQxKiUO1GZDcW1nxNKNP9RXdxuApadvwxQlTc3rwLtvfAvj/mdmwTfHth4jLADuFuF3P3lzn3NoxpDkkDWJ9MOhvOtNck8yOw3wOyLDukFUgxgH2Z5G19OtV+Qut2QHC8e7xfoq+iNna/prVCrbkhPjBv4vZm2zUToy3JptfKtDLXktcFjYP2857aoK71qJK1DXvZzvvwadm6+Hq7XmIRnmozuslm1NRmmDFq1RajtaTF0HTLWYxdktWBhM1kRPYqH8Xs5Wn2RI/0VFtictrIOW1EeP2xFkZb6PpYlF6yKMmiVBaDvKHeOOeMsfbTrEa8txSH+FajjEbVtNUs3Wu0oW8C9zOa4rXU1KI7qIWxVk35w9Kr12lK1a1hzhnu81fa44FQ9T31rEE5pa5tccUDrYK60w7pRIguoydseJZsXXfyVWAr6WIz6hs5wOcLED+h+1rMLLzK9SZGEWt9ZuhcDI+8cvUZfeBnuJqQteSsxFzw0x3MdTFdRjsyI8xYavfC8Gwx7MMszz7s2DnZ9xHN5UueCelL33NCOunfqT2kJT5BparjXJUfUHZC6ydLr4oPyqiRxpd8hB+Q9NzkvBzJHQppxabUCreN7Y19T8xPieGI2tPYsExw7X9k9GBCMvLT/OSYu64mJiPu98luXEmPdJk/34CS4/Nu9/bw9lhbd6Hus1xFFKg9Nu6Y1sblLEaVUWaJ9esP6f9w59z2MSpee6xBLciCKxu8BfuN1r6X74ZPExvbfKLkUK7jncAnMltgibrw2yM/gp/jvMV6MZ85Qdxb+HxHlgJryz/uL3MaGhkxIcl4mqZslGpKO3/u6KW0xYz76qQrJl9VaUp3ZTSlCdL8lN+Ha4IPVZ6GozLNf5EHakWfbms4PvAdSMNX+Xyv+fTo3Gup9dDHsN8MwR/OQadyDjYq52Czcg62KuegWzkHvco52K6cg1b+PH+VPLRqwEP1lrFVA9vYqoF1bBXaxzblXTZgrTbh76aMy380T68KZdTlafvFdLdZC/1FLuqgw036fls9+KiDLiMfddDnJn3DpB58VB/5MB/Vxz/MR/VREPNRj1ioHvFQsyYxUbMmcVGzJrFRsybxUbMkRnoMHzbczEqYOXPmOFZdUjbt164udUWqLqXqUqoupepSqi6l6lKqLj2vpqTqUlwTUnWp+ruE6u8Pqs+0VJ9jqT67Un1epR4Zlep5qEMmpXrLWI8MSvXWsThzwnfwL1O/Ka4pbRhPib9MBrYOupoqSamSlCpJqZL0FD7qEffUI/ZJlaRUSVrFSpKyJb92JUk925QqSamSlCpJqZKUKkmpkpQqSamSlCpJqZKUKkmpkpQqSamSVAUP1dvGemRPyr+ntAl/tyjaeBmelvmekuap9WK6m6pLqbqUqkupupSqS8+Vta+HPa1HTJSqS6m65FeXzOu5NwcfUlRZupAcID58nlWYIqO1wZwbny1ozzyMw9EwV4RVl2PrfYAbcqQw1l5jfsOlmWfzsRnlIIdWVYPfTsm5Rx9ntuA8nV0niGHxrfFc5eCTL08FnzSn3ugZxrqtXgvzVLl2sK1No3vY9SoWIUxG8osnt46o7sLvADWrgnG8HmsPpO4iOJaJ4ZOnBlQPw8qQKb1NudMxvB7Lzcf68IxOlWKJUe+fbNAuXZL8oZwrb4JyeGn0pepoN/SZzx1TO60hfBbKrbVK6jqDETD3OMtx+hqvfN6W47i1chy3V47jzspxvOFw3KYnAeJca3yMc03xVO61bfRtURhnWiNtg107ZFpnDQ2douq2DJ+0qvHvxVDaEPfMF5/CbOfPL4yz54feaSo9kDtDjXPb4KoNhfke6TjebMun/n4J1NzCNGbbt+RD+YxO08OGKdgvs2yF3kztYt0Y57nilO1HxCnmN5rrGa2kWOVXj1XaVM8ttvGbpTZenVuc4pYUt9QjbumUyHSnVKY7KW5JcctKxy36O1AvkV9RI6WIJUUsq5ddwVx8N0UqK8Xx6kcqVXOcopMUnVQTnYTfRPyY/MrWo6KVlF9J0UrKr6SoJUUtKb+SIpgUwTw9gnlMhShlWlLsUt/YJWVaUszys8QsVXOc4pQUp1QZp7jfpXpMpqX3qGglZVpStJIyLSlqSVFLyrSkCCZFME+PYFqPiGBSpiXFLvWNXVKmJcUsP0vMUjXHKU5Jccpj4xQbwt9yPhVmzBCChSIa9eaHxz+5YsPfkZ/RePVeGL8Hl5KjG/RR6o285ttXdXYmRpHRvn6jGS5I52xK/62ixdT4ZsFbihiYw5HUDLefGJXZXr150JWQMAVKyAWNcSU+GTNUb/IJ4ew2IVqb5qyg37NIv2dBWpaBYT6KOT8TrqnOIlRnctVGpGfjwNzDOLtNH+DnBrbptXQpXBn+BJYQtRZ3JByR2xTm+P66hnF2mzKefQqX57NSns8cnm2sfl+r31K/RxI5mpLl6ecSYM7Tx7nj7OZU30CK/gTIfWTUMKUZuYfwbgwf7sOOoE+lr3U1O0blzilkxUMU+OZPXEn2e3MZCyzy+cewiLMt+fPZ9uJvfSbbnmx7su3Jtifbvoq2vbgOmmx7su3Jtifbnmz7y9p2F8aZmiOwJDdkx5jGvj4CHu+FzvXoa7x6Q+v2nd7Ud5OPEoKqkUOwPVrvoZyxvnoP/UzEVFrc+6A3Uu/L/E5Zzkvxkcaeeqvr4zNZn9EnMHE+FU9wwHNO1kQb/rcFZ1URh5KAZ2xsAE7Vie7lnEN9mflQPsWhAz9rAt+Ku+H0q7+Jrno0W6NmYM7Nzi+6UKSbgATw+URHBMdddm1snCrWg2t/41SYnZzAvPYE1qF+px387u1GmCYjS3MO1mNC+UWzvvRR7h9rSowKPTpq8Yj0D3fjlGj7lBW/yFdKr/Zy9LF+dU1rRL58UdCnff5SXMptHeC2KqeKn+13UoZgsdasbWq/QtqE1aAGXJ2Lv6UtUeemtQgzzVvMBZ/zYlPgOOo8KXteQ5KQonOkFsCdPouqQ6edoJ6pE79sfAby+l5aKV9fNa78LKlvOYfsg9Q6uTIbp8usFTsK6moRBeKLVg0p9qmqO5JWBtsdGz1q2TrPdRN7XbZVRn6PIwd9Tosp0yF8BtHFKczGXSkFRZk7If2c5XM9IolEj8XS6+IziljGtEYIWYAs8yxY2y+ldM9IP/nNsLtiIfs4p+hYrdlylA05Ikb0tzQzdTJIw7AtHKvrEwS7hFcy4GK3JPYTRRCzvPKjKtuMYxldRGmUTKiz/biKdE1yYVOuSyl6vrVbkJ9Wd1w7ud11Ya5VsaG2Z6+rv0dv0CXP3JJe3fTL22RbumRdHurxOyQNa/S/lzx+8vgr5PHbpR6//awevw06tkVefDPPH8U8vq+xyePX1eN/kHf2fM9Zb6/fK/H6W4Vev5u8/gp5/Z68w2iRb7d98waM2Hqwt0db1CUL1sufKkzePnn7VfD2nVJv33lWb79JT6qv0VPrKpcb8/a+piZvX1dvfwx+rd4+vlPi43uFPr73E/r4sfysR3uqz9dwM9+vn0Vln3AL0sk86esGPfvMn93M4pTW4AJGOoVf9Fb+Wc0+RUb6NpC2WteOXGhm6Q6O3QCbgL2jV9Q5ULVGmufyGbRrMoP2o2fQqckMOgUzWC+QsHWncmVeM8UHgPTp04EYGpbB/PZU0SnU17KVa/XWoxhcsb28YrgjfZsLVXSs6fbzETYGIXHeGa9nqVZgQtaBIYdAi5V6thrY2qRRK7krLsnfXYnP9BzChNr/H7cQLxE=";
            AdobeTitle title = new AdobeTitle(data);
            System.out.println("decode: " + title.getDataDecode());
            System.out.println();
            System.out.println("xml: " + title.toXML());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
