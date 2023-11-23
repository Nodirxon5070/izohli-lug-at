package com.company.Izohli.lug.at.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedByAudioAPI(){
        String[] array = new String[]{"/audio/create","/audio/get/{id}","/audio/update/{id}","/audio/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Audio")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByCategoryAPI(){
        String[] array = new String[]{"/category/create","/category/get/{id}","/category/update/{id}","/category/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByDayWordAPI(){
        String[] array = new String[]{"/day-word/create","/day-word/get/{id}","/day-word/update/{id}","/day-word/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("DayWord")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByNoteAPI(){
        String[] array = new String[]{"/note/create","/note/get/{id}","/note/update/{id}","/note/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Note")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedBySentenceAPI(){
        String[] array = new String[]{"/sentence/create","/sentence/get/{id}","/sentence/update/{id}","/sentence/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Sentence")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByTypeAPI(){
        String[] array = new String[]{"/type/create","/type/get/{id}","/type/update/{id}","/type/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Type")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByWordAPI(){
        String[] array = new String[]{"/word/create","/word/get/{id}","/word/update/{id}","/word/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("Word")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByWordInSentenceAPI(){
        String[] array = new String[]{"/word-in-sentence/create","/word-in-sentence/get/{id}","/word-in-sentence/update/{id}","/word-in-sentence/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("WordInSentence")
                .pathsToMatch(array)
                .build();
    }

    @Bean
    public GroupedOpenApi groupedByWordTypeAPI(){
        String[] array = new String[]{"/word-type/create","/word-type/get/{id}","/word-type/update/{id}","/word-type/delete/{id}"};
        return GroupedOpenApi.builder()
                .group("WordType")
                .pathsToMatch(array)
                .build();
    }





}
