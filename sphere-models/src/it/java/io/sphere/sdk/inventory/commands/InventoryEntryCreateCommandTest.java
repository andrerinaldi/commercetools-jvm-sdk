package io.sphere.sdk.inventory.commands;

import io.sphere.sdk.channels.ChannelRole;
import io.sphere.sdk.commands.DeleteCommand;
import io.sphere.sdk.inventory.InventoryEntry;
import io.sphere.sdk.inventory.InventoryEntryDraft;
import io.sphere.sdk.test.IntegrationTest;
import org.junit.Test;

import java.time.ZonedDateTime;

import static io.sphere.sdk.channels.ChannelFixtures.withChannelOfRole;
import static io.sphere.sdk.test.SphereTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryEntryCreateCommandTest extends IntegrationTest {
    @Test
    public void execution() throws Exception {
        withChannelOfRole(client(), ChannelRole.INVENTORY_SUPPLY, channel -> {
            final String sku = randomKey();
            final long quantityOnStock = 10;
            final ZonedDateTime expectedDelivery = tomorrowZonedDateTime();
            final int restockableInDays = 3;
            final InventoryEntryDraft inventoryEntryDraft = InventoryEntryDraft.of(sku, quantityOnStock)
                    .withExpectedDelivery(expectedDelivery)
                    .withRestockableInDays(restockableInDays)
                    .withSupplyChannel(channel);

            final InventoryEntry inventoryEntry = client().executeBlocking(InventoryEntryCreateCommand.of(inventoryEntryDraft));

            assertThat(inventoryEntry.getSku()).isEqualTo(sku);
            assertThat(inventoryEntry.getQuantityOnStock()).isEqualTo(quantityOnStock);
            assertThat(inventoryEntry.getAvailableQuantity()).isEqualTo(quantityOnStock);
            assertThat(inventoryEntry.getExpectedDelivery()).isEqualTo(expectedDelivery);
            assertThat(inventoryEntry.getRestockableInDays()).isEqualTo(restockableInDays);
            assertThat(inventoryEntry.getSupplyChannel()).isEqualTo(channel.toReference());

            //delete
            final DeleteCommand<InventoryEntry> deleteCommand = InventoryEntryDeleteCommand.of(inventoryEntry);
            final InventoryEntry deletedEntry = client().executeBlocking(deleteCommand);
        });
    }
}