package org.iesteis.examenud22026.config;

import org.iesteis.examenud22026.model.Vivienda;
import org.iesteis.examenud22026.repository.ViviendaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner loadData(ViviendaRepository repository){
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = new ClassPathResource("data.json").getInputStream();
            List<Vivienda> viviendas = mapper.readValue(input, new TypeReference<List<Vivienda>>(){});
            repository.deleteAll();
            repository.saveAll(viviendas);

        };
    }

}
