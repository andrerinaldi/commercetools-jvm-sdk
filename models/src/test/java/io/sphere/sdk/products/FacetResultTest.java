package io.sphere.sdk.products;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sphere.sdk.search.*;
import io.sphere.sdk.json.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FacetResultTest {
    private static final String TERM_FACET_KEY = "variants.attributes.filterColor.key";
    private static final String RANGE_FACET_KEY = "variants.attributes.priceb2c.centAmount";
    private PagedSearchResult<ProductProjection> pagedSearchResult;

    @Before
    public void setUp() throws Exception {
        pagedSearchResult = JsonUtils.readObjectFromResource("facetResult.json", new TypeReference<PagedSearchResult<ProductProjection>>() { });
    }

    @Test
    public void parsesTermFacetResults() throws Exception {
        final TermFacetResult termFacet = termFacet();
        assertThat(termFacet.getMissing()).isEqualTo(44);
        assertThat(termFacet.getTotal()).isEqualTo(6937);
        assertThat(termFacet.getOther()).isEqualTo(0);
        assertThat(termFacet.getTerms()).hasSize(21);
        assertThat(termFacet.getTerms().get(2)).isEqualTo(TermStats.of("4B432E_1", 585));
    }

    @Test
    public void parsesRangeFacetResults() throws Exception {
        final RangeFacetResult rangeFacet = rangeFacet();
        assertThat(rangeFacet.getRanges()).hasSize(2);
        final RangeStats stats = RangeStats.of(5001, 0, 1799, 1799, 92868378, 5100, 590000, 51622.222345747636);
        assertThat(rangeFacet.getRanges().get(1)).isEqualTo(stats);
    }

    private TermFacetResult termFacet() {
        return (TermFacetResult) pagedSearchResult.getFacetsResults().get(TERM_FACET_KEY);
    }

    private RangeFacetResult rangeFacet() {
        return (RangeFacetResult) pagedSearchResult.getFacetsResults().get(RANGE_FACET_KEY);
    }
}