import { Seat } from "./Seat";

export const Row = ({seats}) => {
    let row = seats.map(e => <Seat>{{row: e.row, column: e.place, style: {background: "#313131"}}}</Seat>);
    return <div row w-50>{row}</div>
}