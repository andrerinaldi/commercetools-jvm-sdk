package io.sphere.sdk.types.commands;

import io.sphere.sdk.categories.Category;
import io.sphere.sdk.models.TextInputHint;
import io.sphere.sdk.test.IntegrationTest;
import io.sphere.sdk.types.FieldDefinition;
import io.sphere.sdk.types.*;
import io.sphere.sdk.types.queries.TypeQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.sphere.sdk.test.SphereTestUtils.en;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeCreateCommandTest extends IntegrationTest {
    @Test
    public void execution() {
        final FieldDefinition stringFieldDefinition =
                FieldDefinition.of(StringFieldType.of(), "string-field-name", en("label"), false, TextInputHint.SINGLE_LINE);
        final String typeKey = "type-key";
        final TypeDraft typeDraft = TypeDraftBuilder.of(typeKey, en("name of the custom type"), singleton(Category.referenceTypeId()))
                .description(en("description"))
                .fieldDefinitions(singletonList(stringFieldDefinition))
                .build();
        final Type type = client().executeBlocking(TypeCreateCommand.of(typeDraft));
        assertThat(type.getKey()).isEqualTo(typeKey);
        assertThat(type.getName()).isEqualTo(en("name of the custom type"));
        assertThat(type.getResourceTypeIds()).containsExactly(Category.referenceTypeId());
        assertThat(type.getDescription()).isEqualTo(en("description"));
        assertThat(type.getFieldDefinitions()).containsExactly(stringFieldDefinition);
    }

    @Before
    @After
    public void cleanUp() throws Exception {
        final TypeQuery typeQuery = TypeQuery.of().withPredicates(type -> type.key().is("type-key"));
        client().executeBlocking(typeQuery)
                .getResults().forEach(type -> client().executeBlocking(TypeDeleteCommand.of(type)));
    }
}