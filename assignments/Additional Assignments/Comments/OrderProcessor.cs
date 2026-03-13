public class OrderProcessor
{
    private readonly IPaymentGateway _paymentGateway;
    private readonly IInventoryService _inventoryService;
    private readonly INotificationService _notificationService;

    public OrderProcessor(
        IPaymentGateway paymentGateway,
        IInventoryService inventoryService,
        INotificationService notificationService)
    {
        _paymentGateway = paymentGateway;
        _inventoryService = inventoryService;
        _notificationService = notificationService;
    }

    public async Task<OrderResult> ProcessOrder(Order order)
    {
        if (order == null)
        {
            throw new ArgumentNullException(nameof(order));
        }

        if (!IsValidOrder(order))
        {
            return OrderResult.Invalid("Order validation failed");
        }

        if (!await IsInventoryAvailable(order))
        {
            return OrderResult.Failed("Insufficient inventory");
        }

        await _inventoryService.ReserveItems(order.Items);

        try
        {
            var paymentResult = await _paymentGateway.ProcessPayment(
                order.CustomerId,
                order.TotalAmount,
                order.PaymentMethod);

            if (paymentResult.IsSuccessful)
            {
                await FinalizeOrder(order);
                return OrderResult.Success(paymentResult.TransactionId);
            }
            else
            {
                await _inventoryService.ReleaseReservation(order.Items);
                return OrderResult.Failed($"Payment failed: {paymentResult.ErrorMessage}");
            }
        }
        catch (Exception exception)
        {
            await HandleProcessingError(order, exception);
            throw;
        }
    }

    public async Task CancelOrder(string orderId)
    {
        var orderToCancel = await GetOrderById(orderId);

        if (IsOrderPaid(orderToCancel))
        {
            await _paymentGateway.RefundPayment(orderToCancel.TransactionId);
            await _inventoryService.RestoreInventory(orderToCancel.Items);
        }

        await MarkOrderAsCancelled(orderToCancel);
    }

    private bool IsValidOrder(Order order)
    {
        return order.Items?.Count > 0 && order.TotalAmount > 0;
    }

    private async Task<bool> IsInventoryAvailable(Order order)
    {
        return await _inventoryService.CheckAvailability(order.Items);
    }

    private async Task FinalizeOrder(Order order)
    {
        await _inventoryService.CommitReservation(order.Items);
        await _notificationService.SendOrderConfirmation(order);
    }

    private async Task HandleProcessingError(Order order, Exception exception)
    {
        await _inventoryService.ReleaseReservation(order.Items);
        LogException(exception);
    }

    private void LogException(Exception exception)
    {
        Console.WriteLine($"Error: {exception.Message}");
    }

    private bool IsOrderPaid(Order order)
    {
        return order.Status == OrderStatus.Paid;
    }

    private async Task MarkOrderAsCancelled(Order order)
    {
        order.Status = OrderStatus.Cancelled;
        await SaveOrder(order);
    }

    private async Task<Order> GetOrderById(string orderId)
    {
        //TODO: implement the method
        return await Task.FromResult(new Order());
    }

    private async Task SaveOrder(Order order)
    {
        //TODO: implement the method
        await Task.CompletedTask;
    }
}
