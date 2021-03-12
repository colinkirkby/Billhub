import React from 'react';
import { useBlockLayout, useTable } from 'react-table';
import './General.css';
import './Glossary.css';

/* Code from https://blog.logrocket.com/complete-guide-building-smart-data-table-react/ used */

function GlossaryTable({ columns, data })
{
    const defaultColumn = React.useMemo (
      () => ({
        width: 400,
      }),
      []
    )
    const {
        getTableProps, 
        getTableBodyProps, 
        headerGroups, 
        rows, 
        prepareRow 
      } = useTable({
        columns,
        data,
        defaultColumn,
      }, 
        useBlockLayout
    )

      return (
        <table className = 'table' {...getTableProps()}>
          <thead >
            {headerGroups.map(headerGroup => (
              <tr {...headerGroup.getHeaderGroupProps()}>
                {headerGroup.headers.map(column => (
                  <th className = 'table-header' {...column.getHeaderProps()}>{column.render("Header")}</th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {rows.map((row, i) => {
              prepareRow(row);
              return (
                <tr className = 'separating-line' {...row.getRowProps()}>
                  {row.cells.map(cell => {
                    return <td className = 'table-data' {...cell.getCellProps()}>{cell.render("Cell")}</td>;
                  })}
                </tr>
              );
            })}
          </tbody>
        </table>
      );
}

export default GlossaryTable