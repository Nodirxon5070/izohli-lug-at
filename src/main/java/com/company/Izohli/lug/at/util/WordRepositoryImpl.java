package com.company.Izohli.lug.at.util;

import com.company.Izohli.lug.at.module.Word;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WordRepositoryImpl {
    private final EntityManager entityManager;


    public Page<Word> searchWordByMoreParams(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        String firstQuery = "select w from Word as w where true ";
        String secondQuery = "select count(w.wordId) from Word as w where true ";

        StringBuilder firstBuildQuery = buildParams(params);

        Query queryOne = entityManager.createQuery(firstQuery + firstBuildQuery);
        Query queryTwo = this.entityManager.createQuery(secondQuery + firstBuildQuery);

        queryOne.setFirstResult(size * page);
        queryOne.setMaxResults(size);

        setMoreParams(params, queryOne);
        setMoreParams(params, queryTwo);


        return new PageImpl<Word>(
                queryOne.getResultList(),
                PageRequest.of(page, size),
                Long.parseLong(queryTwo.getSingleResult().toString())
        );
    }
    private void setMoreParams(Map<String, String> params, Query query) {
        if (params.containsKey("word_id")) {
            query.setParameter("word_id", params.get("word_id"));
        }
        if (params.containsKey("label")) {
            query.setParameter("label", params.get("label"));
        }
        if (params.containsKey("transcript")) {
            query.setParameter("transcript", params.get("transcript"));
        }
        if (params.containsKey("categoryId")) {
            query.setParameter("categoryId", params.get("categoryId"));
        }

        if (params.containsKey("audioId")) {
            query.setParameter("audioId", params.get("audioId"));
        }

        if (params.containsKey("numView")) {
            query.setParameter("numView", params.get("numView"));
        }

        if (params.containsKey("numLike")) {
            query.setParameter("numLike", params.get("numLike"));
        }

        if (params.containsKey("numShare")) {
            query.setParameter("numShare", params.get("numShare"));
        }

        if (params.containsKey("createdAt")) {
            query.setParameter("createdAt", params.get("createdAt"));
        }

        if (params.containsKey("deletedAt")) {
            query.setParameter("deleted_at", params.get("deletedAt"));
        }

        if (params.containsKey("updatedAt")) {
            query.setParameter("updatedAt", params.get("updatedAt"));
        }
    }

    private StringBuilder buildParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("word_id")) {
            stringBuilder.append("w.wordId = :wordId");
        }
        if (params.containsKey("label")) {
            stringBuilder.append(" AND w.label = :label");
        }
        if (params.containsKey("transcript")) {
            stringBuilder.append(" AND w.transcript = :transcript");
        }
        if (params.containsKey("categoryId")) {
            stringBuilder.append(" AND w.categoryId = :categoryId");
        }
        if (params.containsKey("audioId")) {
            stringBuilder.append(" AND w.audioId = :audioId");
        }

        if (params.containsKey("numLike")) {
            stringBuilder.append(" AND w.numLike => :numLike");
        }

        if (params.containsKey("numShare")) {
            stringBuilder.append(" AND w.numShare => :numShare");
        }

        if (params.containsKey("numView")) {
            stringBuilder.append(" AND w.numView => :numView");
        }

        if (params.containsKey("createdAt")) {
            stringBuilder.append(" AND w.createdAt = :createdAt");
        }

        if (params.containsKey("updatedAt")) {
            stringBuilder.append(" AND w.updatedAt = :updatedAt");
        }

        if (params.containsKey("deletedAt")) {
            stringBuilder.append(" AND w.deletedAt = :deletedAt");
        }

        return stringBuilder;
    }

}
