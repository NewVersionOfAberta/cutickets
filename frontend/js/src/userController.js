export const userController = {
  tickets: [],

  getTicket(id) {
    const ticket = this.tickets.find((e) => e.id == id);
    return ticket;
  },
};
