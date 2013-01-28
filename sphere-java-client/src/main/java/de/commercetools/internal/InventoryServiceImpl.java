package de.commercetools.internal;

import de.commercetools.internal.request.RequestFactory;
import de.commercetools.sphere.client.FetchRequest;
import de.commercetools.sphere.client.ProjectEndpoints;
import de.commercetools.sphere.client.model.QueryResult;
import de.commercetools.sphere.client.model.Reference;
import de.commercetools.sphere.client.shop.InventoryService;
import de.commercetools.sphere.client.shop.model.Catalog;
import de.commercetools.sphere.client.shop.model.InventoryEntry;
import org.codehaus.jackson.type.TypeReference;

public class InventoryServiceImpl extends ProjectScopedAPI implements InventoryService {
    private final RequestFactory requestFactory;

    public InventoryServiceImpl(RequestFactory requestFactory, ProjectEndpoints endpoints) {
        super(endpoints);
        this.requestFactory = requestFactory;
    }

    /** {@inheritDoc}  */
    public FetchRequest<InventoryEntry> byId(String id) {
        return requestFactory.createFetchRequest(endpoints.inventory.byId(id), new TypeReference<InventoryEntry>() {});
    }

    /** {@inheritDoc}  */
    public FetchRequest<InventoryEntry> byProductIdVariantIdCatalog(String productId, int variantId, Reference<Catalog> catalog) {
        return requestFactory.createFetchRequestBasedOnQuery(
                endpoints.inventory.byProductIdVariantIdCatalog(productId, variantId, catalog),
                new TypeReference<QueryResult<InventoryEntry>>() {});
    }

    /** {@inheritDoc}  */
    public FetchRequest<InventoryEntry> byProductIdVariantIdInMasterCatalog(String productId, int variantId) {
        return byProductIdVariantIdCatalog(productId, variantId, null);
    }
}
