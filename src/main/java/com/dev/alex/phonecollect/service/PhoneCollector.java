package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.utils.PrepareHttpsConnection;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PhoneCollector {


    public String collectPhones(@NotNull OperatorEnum operatorEnum) throws IOException {

        final HttpsURLConnection con = PrepareHttpsConnection.prepareConnection(operatorEnum);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
